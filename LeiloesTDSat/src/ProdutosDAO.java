/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

   public int cadastrarProduto(ProdutosDTO produto) {
    int status = 0;
    try {
       

        // Conexão com o banco de dados
        conn = new conectaDAO().connectDB();
        // Preparação da SQL para inserção do produto
        prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());

        // Executa a query
        status = prep.executeUpdate();

        // Exibe a mensagem de sucesso ou erro após tentar inserir o produto
        if (status > 0) {
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        // Captura de erro de SQL
        System.out.println("Erro ao conectar: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        return ex.getErrorCode(); // Retorna o código do erro caso ocorra uma exceção.
    } catch (Exception ex) {
        // Captura de qualquer outro erro inesperado
        System.out.println("Erro inesperado: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } finally {
    
        desconectar();
    }
    
    return status;
}

public void desconectar() {
    try {
        // Fechar o PreparedStatement e a conexão, se eles não forem nulos
        if (prep != null) {
            prep.close();
            System.out.println("PreparedStatement fechado...");
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("Conexão fechada...");
        }
    } catch (SQLException ex) {
        // Caso ocorra algum erro ao fechar a conexão ou o PreparedStatement
        System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
    }
}

        



public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
