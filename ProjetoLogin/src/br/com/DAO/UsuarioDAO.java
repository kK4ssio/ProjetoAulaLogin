package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author Kassio Dias Monteiro
 */
public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void inserirUsuario(UsuarioDTO objUsuarioDTO) {
        String sql = "insert into tb_usuarios(id_usuario, usuario, login, senha) values(?, ?, ?, ?) ";

        conexao = new ConexaoDao().conector();

        try {

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getId_Usuario());
            pst.setString(2, objUsuarioDTO.getNomeUsuario());
            pst.setString(3, objUsuarioDTO.getLoginUsuario());
            pst.setString(4, objUsuarioDTO.getSenhaUsuario());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

            pst.close();

        } catch (Exception e) {
            if (e.getMessage().contains("'tb_usuarios.PRIMARY'")) {
                JOptionPane.showMessageDialog(null, "id ja existente");
                
            } else if (e.getMessage().contains("'tb_usuarios.login'")) {
                JOptionPane.showMessageDialog(null, "login ja existente");
            }
            JOptionPane.showMessageDialog(null, "Usuario não foi inserido ");

        }
    }
}
