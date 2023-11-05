import pandas as pd
import pyodbc
import time
import joblib

start_time = time.time()

server = 'DESKTOP-6BMLQQ6\SQLSERVER'
database = 'BARBERSHOP12'
username = 'SA'
password = '352636'

connection_string = f"DRIVER={{ODBC Driver 17 for SQL Server}};SERVER={server};DATABASE={database};UID={username};PWD={password}"

conn = pyodbc.connect(connection_string)
cursor = conn.cursor()



# ***********************************************
model = joblib.load('svm_model.pkl')
stored_proc_name = "NhapHang_TonKho"

query = f"EXEC {stored_proc_name}"
dt = pd.read_sql_query(query, conn)

X_new = dt[['LuongTon', 'SoLuongNhap', 'NgayNhapHangGanNhat']]

y_pred = model.predict(X_new)
print(y_pred)

labels = ["khongnhap" if pred == 0 else "nhap" for pred in y_pred]

sql_merge = """
MERGE INTO HocMay AS target
USING (VALUES (?, ?, ?, ?, ?, ?)) AS source (MaSanPham, TenSanPham, ThoiGianNhap, SoLuongTon, SoLuongNhap, Nhan)
ON target.MaSanPham = source.MaSanPham
WHEN MATCHED THEN
    UPDATE SET target.Nhan = source.Nhan,target.ThoiGianNhap = source.ThoiGianNhap,
    target.SoLuongTon = source.SoLuongTon,target.SoLuongNhap = source.SoLuongNhap
WHEN NOT MATCHED THEN
    INSERT (MaSanPham, TenSanPham, ThoiGianNhap, SoLuongTon, SoLuongNhap, Nhan)
    VALUES (source.MaSanPham, source.TenSanPham, source.ThoiGianNhap, source.SoLuongTon, source.SoLuongNhap, source.Nhan);
"""

print('MaSP', 'TenSP', 'LuongTon', 'SoLuongNhap', 'ThoiGian', 'Nhan')

for i, label in enumerate(labels):
    masp = dt['MaSanPham'].iloc[i]
    tensp = dt['TenSanPham'].iloc[i]
    thoigian = int(dt['NgayNhapHangGanNhat'].iloc[i])
    luongton = int(dt['LuongTon'].iloc[i])
    soluongnhap = int(dt['SoLuongNhap'].iloc[i])
    nhan = label

    print(f"{masp}, {tensp}, {luongton}, {soluongnhap}, {thoigian}: {label}")
    try:
        cursor.execute(sql_merge, (masp, tensp, thoigian, luongton, soluongnhap, nhan))
        conn.commit()
    except Exception as e:
        print(f"Lỗi: {str(e)}")

conn.close()

end_time = time.time()

elapsed_time = end_time - start_time

print(f"Thời gian chạy mô hình: {elapsed_time} giây")
