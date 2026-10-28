# Código Antigo — Sistema de Aprovação de Despesas

Versão original, antes da refatoração com o padrão **Chain of Responsibility**.

## Problema

A classe `ExpenseApprovalService` conhece todos os níveis de aprovação e concentra suas regras em uma sequência fixa de condicionais.

- Incluir um novo responsável ou reorganizar o fluxo exige modificar essa classe.
- Não é possível montar diferentes sequências de aprovação combinando os responsáveis disponíveis.
- O solicitante só precisa que a despesa seja tratada, mas o serviço central decide diretamente qual responsável vai atuar.

## ExpenseRequest.java

```java
public class ExpenseRequest {

    private String description;
    private int amountInCents;

    public ExpenseRequest(String description, int amountInCents) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (amountInCents <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.description = description;
        this.amountInCents = amountInCents;
    }

    public String getDescription() {
        return description;
    }

    public int getAmountInCents() {
        return amountInCents;
    }
}
```

## ExpenseApprovalService.java

```java
public class ExpenseApprovalService {

    public void approve(ExpenseRequest request) {
        int amount = request.getAmountInCents();

        if (amount <= 50000) {
            System.out.println("Team leader approved: " + request.getDescription());
        } else if (amount <= 200000) {
            System.out.println("Manager approved: " + request.getDescription());
        } else if (amount <= 1000000) {
            System.out.println("Director approved: " + request.getDescription());
        } else {
            System.out.println("No approver available: " + request.getDescription());
        }
    }
}
```

## Main.java

```java
public class Main {

    public static void main(String[] args) {

        ExpenseApprovalService service = new ExpenseApprovalService();

        service.approve(new ExpenseRequest("Office supplies", 30000));
        service.approve(new ExpenseRequest("Training", 150000));
        service.approve(new ExpenseRequest("Equipment", 800000));
        service.approve(new ExpenseRequest("Office renovation", 1500000));
    }
}
```
