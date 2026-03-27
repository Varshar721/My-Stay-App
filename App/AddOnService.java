public class AddOnService {
    private String serviceName;
    private double serviceCost;

    public AddOnService(String serviceName, double serviceCost) {
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void displayServiceDetails() {
        System.out.println("Service Name : " + serviceName);
        System.out.println("Service Cost : Rs." + serviceCost);
        System.out.println("----------------------------------------");
    }
}