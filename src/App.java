import br.com.gui.domain.Cliente;
import dao.ClienteMapDAO;
import dao.IClienteDAO;

import javax.swing.*;

public class App {
    private static IClienteDAO iClienteDAO;

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null, "Digite 1 para Cadastro, 2 para Consultar, 3 para Exclusão, " +
                        "4 para Alteração ou 5 para Sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null, "Opção inválida! Digite 1 para Cadastro, 2 para Consulta, 3 para Excluir, " +
                    "4 para Alterar ou 5 para Sair", "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do cliente separados por vígula, conforme exemplo: Nome, CPF, " +
                        "Telefone, Endereço, Número, Cidade e Estado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF", "Consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isExcluir(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF", "Excluir", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do cliente separados por vígula, conforme exemplo: Nome, CPF, " +
                        "Telefone, Endereço, Número, Cidade e Estado", "Atulaização", JOptionPane.INFORMATION_MESSAGE);
                alterar(dados);
        }
            opcao = JOptionPane.showInputDialog(null, "Digite 1 para Cadastro, 2 para Consultar, 3 para Exclusão, " +
                    "4 para Alteração ou 5 para Sair", "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void consultar(String dados) {
        //Validar se foi passado somente o CPF
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado: ", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }
    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        //Tentar validar se todos os campos estão preenchidos.
        //Se não tiver, passar null no construtor onde o valor é nulo
        //Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1],null,dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6])
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        Boolean isCadastro = iClienteDAO.cadastrar(cliente);
        if (isCadastro) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }
    private static boolean isExcluir(String opcao) {
        if ("3".equals(opcao)) {
            return true;
        }
        return false;
    }
    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }
    private static void alterar(String dados) {
        String[] isAtualizar = dados.split(",");
        Cliente cliente = new Cliente(isAtualizar[0], isAtualizar[1], isAtualizar[2], isAtualizar[3], isAtualizar[4],
                isAtualizar[5], isAtualizar[6]);
        iClienteDAO.alterar(cliente);
    }
    private static void excluir(String dados) {
        iClienteDAO.excluir(Long.parseLong(dados));
        JOptionPane.showMessageDialog(null, "Cliente Excluido! ", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}