package com.javier.testecadastro.helper;

import com.javier.testecadastro.model.User;

import java.util.List;

public interface IUserDAO {

    public boolean salvar(User user);
    public boolean atualizar(User user);
    public boolean deletar(User user);
    public List<User> listar();

}


