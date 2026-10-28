public class ExpenseApprovalService {

    private final ExpenseApprover firstApprover;

    public ExpenseApprovalService(ExpenseApprover firstApprover) {
        this.firstApprover = firstApprover;
    }

    public void approve(ExpenseRequest request) {
        firstApprover.approve(request);
    }
}
