import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.svm import SVC
from sklearn.metrics import mean_absolute_error
from sklearn.model_selection import train_test_split
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

df = pd.read_csv('Du_lieu_huan_luyen2.txt')
print(df)

labels = df['Nhan'].copy()

scaler = StandardScaler()
df[['LuongTon', 'SoLuongNhap', 'NgayNhapHangGanNhat']] = scaler.fit_transform(df[['LuongTon', 'SoLuongNhap', 'NgayNhapHangGanNhat']])
X = df[['LuongTon', 'SoLuongNhap', 'NgayNhapHangGanNhat']]
y = df['Nhan']
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)



model = SVC(kernel='linear', C=0.544)
model.fit(X_train, y_train)
print(f"Số lượng hỗ trợ vectors: {len(model.support_)}")
print(f"Kích thước ma trận hệ số: {model.coef_.shape}")
y_pred = model.predict(X_test)
print(X_test)

mae = mean_absolute_error(y_test, y_pred)
print(f"Mean Absolute Error: {mae}")


joblib.dump(model, 'svm_model.pkl')

print("Model trained and saved successfully.")
