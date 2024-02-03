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

    public ConversionDevice() {
    }

    public String getPrompt() {
        return prompt;
    }

    public String getSource() {
        return source;
    }

    public String getCible() {
        return cible;
    }

    public double getTaux() {
        return taux;
    }
}
