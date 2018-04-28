package drawer.soar.com.view;

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/4/28
 * ※ Time : 14:09
 * ※ Project : CarmerDrawer
 * ※ Package : drawer.soar.com.view
 * ----------------------------------------------------
 */

public class ListAttr{
    private int id;
    private String menuName;
    private int menuPic;
    private int menuPic_pressed;
    private int menuPic_selected;
    private boolean enable;
    private boolean isSelect;


    public ListAttr(int id, String menuName, int menuPic, int menuPic_pressed,int menuPic_selected,
            boolean enable,boolean isSelect) {
        super();
        this.id = id;
        this.menuName = menuName;
        this.menuPic = menuPic;
        this.menuPic_pressed=menuPic_pressed;
        this.menuPic_selected=menuPic_selected;
        this.enable=enable;
        this.isSelect = isSelect;
    }

    public int getMenuPic() {
        return menuPic;
    }

    public void setMenuPic(int menuPic) {
        this.menuPic = menuPic;
    }

    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMenuPic_pressed() {
        return menuPic_pressed;
    }
    public void setMenuPic_pressed(int menuPic_pressed) {
        this.menuPic_pressed = menuPic_pressed;
    }

    public int getMenuPic_selected() {
        return menuPic_selected;
    }
    public void setMenuPic_selected(int menuPic_selected) {
        this.menuPic_selected = menuPic_selected;
    }
    public boolean isEnable() {
        return enable;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}