package graphics;

import javax.swing.Icon;

public class CardValue 
{
    private Icon icon;
    private String title;
    private String values;
    private String description;

    public CardValue() {}
    
    public CardValue(Icon icon, String title, String values, String description) 
    {
        this.icon = icon;
        this.title = title;
        this.values = values;
        this.description = description;
    }
    
    public Icon getIcon() 
    {
        return this.icon;
    }

    public void setIcon(Icon icon) 
    {
        this.icon = icon;
    }

    public String getTitle() 
    {
        return this.title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getValues() 
    {
        return this.values;
    }

    public void setValues(String values) 
    {
        this.values = values;
    }

    public String getDescription() 
    {
        return this.description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }
}