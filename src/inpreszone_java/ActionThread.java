/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inpreszone_java;

/**
 *
 * @author Sh1fT
 */

public final class ActionThread extends Thread {
    protected InpresZone parent;

    /**
     * Creates new instance ActionThread
     * @param parent 
     */
    public ActionThread(InpresZone parent) {
        this.setParent(parent);
    }

    public InpresZone getParent() {
        return this.parent;
    }

    protected void setParent(InpresZone parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        this.getParent().getProductProgressBar().setVisible(true);
        this.getParent().pack();
        this.getParent().getOkButton().setEnabled(false);
        this.getParent().getProductJListModel().removeAllElements();
        if (this.getParent().getCritereRadioButton().isSelected()) {
            this.getParent().setProductList(null);
            if (this.getParent().getArtisteCheckBox().isSelected()) {
                if (this.getParent().getCategorieCheckBox().isSelected()) {
                    if (this.getParent().getCategorieComboBox().getSelectedItem().toString().compareTo("book") == 0)
                        this.getParent().feedByAuthor(this.getParent().getArtisteTextField().getText());
                    else if (this.getParent().getCategorieComboBox().getSelectedItem().toString().compareTo("movie") == 0)
                        this.getParent().feedByActor(this.getParent().getArtisteTextField().getText());
                    else
                        this.getParent().feedByArtist(this.getParent().getArtisteTextField().getText());
                }
            }
            this.getParent().getProductProgressBar().setValue(12);
            if (this.getParent().getPrixCheckBox().isSelected())
                this.getParent().feedByPrice((Double) this.getParent().getPrixMinSpinner().getValue(),
                    (Double) this.getParent().getPrixMaxSpinner().getValue());
            this.getParent().getProductProgressBar().setValue(24);
            if (this.getParent().getCategorieCheckBox().isSelected())
                this.getParent().feedByCategory(this.getParent().getCategorieComboBox().getSelectedItem().toString());
            this.getParent().getProductProgressBar().setValue(36);
            if (this.getParent().getLangueCheckBox().isSelected()) {
                if (this.getParent().getCategorieComboBox().getSelectedItem().toString().compareTo("movie") == 0)
                    this.getParent().feedByLanguages(this.getParent().getLangueTextField().getText());
                else if (this.getParent().getCategorieComboBox().getSelectedItem().toString().compareTo("book") == 0)
                    this.getParent().feedByLanguage(this.getParent().getLangueTextField().getText());
            }
        } else if(this.getParent().getAsinRadioButton().isSelected())
            this.getParent().feedByAsin(this.getParent().getAsinTextField().getText());
        this.getParent().getProductProgressBar().setValue(50);
        if (this.getParent().getProductList() != null) {
            if (this.getParent().getSelectedDB().compareTo("DB_US") == 0)
                this.getParent().insertForBook();
            else if (this.getParent().getSelectedDB().compareTo("DB_UK") == 0)
                this.getParent().insertForMovie();
            else if (this.getParent().getSelectedDB().compareTo("DB_BEL") == 0)
                this.getParent().insertForDbBel();
            this.getParent().getProductProgressBar().setValue(100);
        }
        this.getParent().getOkButton().setEnabled(true);
        this.getParent().getProductProgressBar().setVisible(false);
        this.getParent().pack();
    }
}