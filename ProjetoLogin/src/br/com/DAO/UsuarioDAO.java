package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import br.com.VIEWS.TelaUsuarios;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author Kassio Dias Monteiro
 */
public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //Metodo Inserir
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

    public void editar(UsuarioDTO objUsuarioDTO) {
        String sql = "update tb_usuarios set usuario = ?, login = ?, senha = ? where id_usuario = ?";
        conexao = ConexaoDao.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(4, objUsuarioDTO.getId_Usuario());
            pst.setString(1, objUsuarioDTO.getNomeUsuario());
            pst.setString(2, objUsuarioDTO.getLoginUsuario());
            pst.setString(3, objUsuarioDTO.getSenhaUsuario());

            int add = pst.executeUpdate();

            if (add > 0) {
                JOptionPane.showMessageDialog(null, " Usuario editado com sucesso ");
                //pesquisaAuto();
                conexao.close();
                Limpar();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " metodo editar " + e);
            Limpar();
        }
    }

    public void deletar(UsuarioDTO objUsuarioDTO) {
        String sql = "delete from tb_usuarios where id_usuario = ?";
        conexao = ConexaoDao.conector();

        try {

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getId_Usuario());

            int del = pst.executeUpdate();

            if (del > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Usuario deletado");
                Limpar();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " metodo apagar " + e);
            Limpar();
        }

    }

    public void Limpar() {

        TelaUsuarios.txtIdUsuario.setText(null);
        TelaUsuarios.txtNomeUsuario.setText(null);
        TelaUsuarios.txtLogin.setText(null);
        TelaUsuarios.txtSenha.setText(null);

    }

    public void pesquisar(UsuarioDTO objUsuarioDTO) {
        String sql = "select * from tb_usuarios where id_usuario = ?";
        conexao = ConexaoDao.conector();

        try {

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getId_Usuario());
            rs = pst.executeQuery();

            if (rs.next()) {

                TelaUsuarios.txtNomeUsuario.setText(rs.getString(2));
                TelaUsuarios.txtLogin.setText(rs.getString(3));
                TelaUsuarios.txtSenha.setText(rs.getString(4));
                conexao.close();

            } else {
                JOptionPane.showMessageDialog(null, "Usuario não cadastrado");
                Limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "tela Usuario" + e);
        }
    }
}
