package ru.otus.spring.domain;

public class Account {
    private String id;
    private String personId;
    private Long amount;
    
    public Account(String id, String personId, Long amount) {
        this.id = id;
        this.personId = personId;
        this.amount = amount;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPersonId() {
        return personId;
    }
    
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public Long getAmount() {
        return amount;
    }
    
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
