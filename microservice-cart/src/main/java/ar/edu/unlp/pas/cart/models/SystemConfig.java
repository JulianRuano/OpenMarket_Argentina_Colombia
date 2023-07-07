package ar.edu.unlp.pas.cart.models;

import javax.persistence.*;

@Entity()
public class SystemConfig {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commission")
    private Double commission;

    @Column(name = "max_opinions_to_show")
    private int maxOpinionsToShow;

    public SystemConfig(Double commission, int maxOpinionsToShow) {
        this.commission = commission;
        this.maxOpinionsToShow = maxOpinionsToShow;
    }

    public SystemConfig() {
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
