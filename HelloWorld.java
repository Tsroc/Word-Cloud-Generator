public class HelloWorld{
    public static void main(String[] args){
        System.out.println("HelloWorld");
        WCGmenu menu = new WCGmenu();
        menu.displayMenu();
        menu.readFile();
        //menu.frequencyTable = menu.getShortTable();
        menu.frequencyTable = menu.sortTable();
        //menu.frequencyTable = menu.getShortTable();
        menu.displayTable();

        //System.out.println(menu.getIgnoreList());
    }
}