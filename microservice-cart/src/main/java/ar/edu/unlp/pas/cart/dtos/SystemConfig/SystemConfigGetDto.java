package ar.edu.unlp.pas.cart.dtos.SystemConfig;

public class SystemConfigGetDto {
    private Double commission;

    private int maxOpinionsToShow;

    public SystemConfigGetDto(Double commission, int maxOpinionsToShow) {
        this.commission = commission;
        this.maxOpinionsToShow = maxOpinionsToShow;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public int getMaxOpinionsToShow() {
        return maxOpinionsToShow;
    }

    public void setMaxOpinionsToShow(int maxOpinionsToShow) {
        this.maxOpinionsToShow = maxOpinionsToShow;
    }
}
