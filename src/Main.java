public class Main {

    public static void main(String[] args) {

        ExpenseApprover teamLeader = new TeamLeaderApprover();
        ExpenseApprover manager = new ManagerApprover();
        ExpenseApprover director = new DirectorApprover();

        teamLeader.setNext(manager);
        manager.setNext(director);
        director.setNext(null);

        ExpenseApprovalService service = new ExpenseApprovalService(teamLeader);

        System.out.println("=== Valores dentro das faixas ===");
        service.approve(new ExpenseRequest("Office supplies", 30000));      // R$ 300,00
        service.approve(new ExpenseRequest("Training", 150000));            // R$ 1.500,00
        service.approve(new ExpenseRequest("Equipment", 800000));           // R$ 8.000,00
        service.approve(new ExpenseRequest("Office renovation", 1500000));  // R$ 15.000,00

        System.out.println();
        System.out.println("=== Valores exatos nos limites ===");
        service.approve(new ExpenseRequest("Team leader limit", 50000));    // R$ 500,00
        service.approve(new ExpenseRequest("Manager limit", 200000));       // R$ 2.000,00
        service.approve(new ExpenseRequest("Director limit", 1000000));     // R$ 10.000,00
    }
}
