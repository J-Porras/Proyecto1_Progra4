/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Usuarios.logica.Usuarios;

/**
 *
 * @author pgat3000
 */
public class Model_Login {
    Usuarios current_user ;

    public Model_Login() {
        current_user= null;
    }

    public Usuarios getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(Usuarios current_user) {
        this.current_user = current_user;
    }
    
}
