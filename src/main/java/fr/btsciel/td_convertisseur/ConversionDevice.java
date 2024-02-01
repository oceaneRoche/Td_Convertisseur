package fr.btsciel.td_convertisseur;

public class ConversionDevice {
    private String prompt, source, cible;
    private double taux;

    public ConversionDevice(String prompt, String source, String cible, double taux) {
        this.prompt=prompt;
        this.source=source;
        this.cible=cible;
        this.taux=taux;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCible() {
        return cible;
    }

    public void setCible(String cible) {
        this.cible = cible;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
