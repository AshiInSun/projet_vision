package Vue;

public class Redessine extends Thread{
    private Affichage affichage;
    private InventoryVue inventoryVue;
    public int DELAY=1;

    public Redessine(Affichage affichage, InventoryVue inventaire){
        this.affichage = affichage;
        this.inventoryVue = inventaire;
    }

    @Override
    public void run(){
        while (true){
            affichage.revalidate();
            affichage.repaint();
            inventoryVue.getInventory().updateBle();
            inventoryVue.updateBleQuantity();
            inventoryVue.revalidate();
            inventoryVue.repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
