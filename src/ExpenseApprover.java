public abstract class ExpenseApprover {

    private ExpenseApprover next;

    public void setNext(ExpenseApprover next) {
        this.next = next;
    }

    public void approve(ExpenseRequest request) {
        if (request.getAmountInCents() <= getLimitInCents()) {
            System.out.println(getApproverName() + " approved: " + request.getDescription());
        } else if (next != null) {
            next.approve(request);
        } else {
            System.out.println("No approver available: " + request.getDescription());
        }
    }

    protected abstract int getLimitInCents();

    protected abstract String getApproverName();
}
