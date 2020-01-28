
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modelo;
import vista.Vista;

/**
 *
 * @author BEATRIZ
 */
public class Controlador implements ActionListener {

    private Vista view;
    private Modelo model;

    public Controlador(Vista view, Modelo model) {
        this.view = view;
        this.model = model;
        this.view.btnSumar.addActionListener(this);
    }

    public void iniciar() {
        view.setTitle("MVC Sumar");
        view.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        model.setNumeroUno(Integer.parseInt(view.txtNumeroUno.getText()));
        model.setNumeroDos(Integer.parseInt(view.txtNumeroDos.getText()));
        model.sumar();
        view.txtResultado.setText(String.valueOf(model.getResultado()));

    }

}
