package it.svitali.dashboard.model;

public enum TicketStatus {
	
	NOT_STARTED("NOT STARTED"),
	
	STARTED("STARTED"),
	
	COMPLETED("COMPLETED");	
	
	
	
    private final String displayValue;
    
    private TicketStatus(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}


