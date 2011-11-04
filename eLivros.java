import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;  
import javax.swing.JTextArea;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener; 
import java.lang.Integer; 

public class eLivros implements ActionListener
{
    // instance variables - replace the example below with your own
    private JFrame window;
    private JTable table;
    private JScrollPane tablePane;
    private JMenuBar menu;
    
    private JMenu fileMenu;
    private JMenuItem[] fileMenuItens;
    private JMenuItem[] livroMenuItens;
    private JMenu helpMenu;
    private String mode;
    /**
     * Constructor for objects of class eLivros
     */
    public static void main(String[] args){
        eLivros handler = new eLivros();
    }
    public eLivros()
    {
        // initialise instance variables
        this.window = new JFrame("eLivros");
        this.window.setLayout(null);
        this.window.setSize(400,450);
        this.fileMenu = new JMenu("Arquivo");
        this.fileMenuItens = new JMenuItem[3];
        JMenuItem newItem = new JMenuItem("Novo Livro");
        newItem.addActionListener(this);
        this.fileMenu.add(newItem);
        JMenuItem edit = new JMenuItem("Editar");
        edit.addActionListener(this);
        this.fileMenu.add(edit);
        JMenuItem deleteItem = new JMenuItem("Apagar");
        deleteItem.addActionListener(this);
        this.fileMenu.add(deleteItem);
        JMenuItem refreshItem = new JMenuItem("Atualizar");
        refreshItem.addActionListener(this);
        this.fileMenu.add(refreshItem);
        this.fileMenu.addSeparator();
        JMenuItem emprestarItem = new JMenuItem("Solicitar Empréstimo");
        emprestarItem.addActionListener(this);
        this.fileMenu.add(emprestarItem);
        JMenuItem devolverItem = new JMenuItem("Devolver Livro");
        devolverItem.addActionListener(this);
        this.fileMenu.add(devolverItem);
        this.fileMenu.addSeparator();
        JMenuItem see = new JMenuItem("Ver Estantes");
        see.addActionListener(this);
        this.fileMenu.add(see);
        this.fileMenu.addSeparator();
        JMenuItem closeWindow = new JMenuItem("Fechar");
        closeWindow.addActionListener(this);
        this.fileMenu.add(closeWindow);
        
        this.helpMenu = new JMenu("Ajuda");
        JMenuItem aboutWindow = new JMenuItem("Sobre");
        aboutWindow.addActionListener(this);
        this.helpMenu.add(aboutWindow);
        this.menu = new JMenuBar();
        this.menu.add(this.fileMenu);
        this.menu.add(this.helpMenu);
        this.window.setJMenuBar(this.menu);
        this.table = Livro.lista();
        this.tablePane = new JScrollPane(this.table);
        this.tablePane.setOpaque(true);
        this.window.setContentPane(this.tablePane);
        this.window.pack();
        this.window.setVisible(true);
        this.mode = "livro";
    }
    public void atualizar(){
        if(this.mode == "livro"){
            this.table = Livro.lista();
        }
        else if(this.mode == "estante"){
            this.table = Estante.lista();
        }
        else{
            this.table = Emprestimo.lista();
        }
        this.tablePane = new JScrollPane(this.table);
        this.tablePane.setOpaque(true);
        this.window.setContentPane(this.tablePane);
        this.window.setVisible(false);
        this.window.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {  
        JMenuItem item = (JMenuItem)e.getSource();
        String itemText = item.getText();
        if(itemText.substring(0,3).equals("Ver")){
            this.mode = this.mode == "livro"?"estante":(this.mode == "emprestimo"?"livro":"emprestimo");
            itemText = "Ver "+(this.mode == "livro"?"Estantes":(this.mode == "estante"?"Empréstimos":"Livros"));
            this.fileMenu.getItem(0).setText((this.mode == "livro"?"Novo Livro":(this.mode == "estante"?"Nova Estante":"Novo Empréstimo")));
            item.setText(itemText);
            this.atualizar();
        }
        else if(itemText.equals("Editar")){
            int row = this.table.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(null,"Selecione uma linha na tabela");
            }
            else{
                if(this.mode == "livro"){
                    Livro.getLivro(Integer.parseInt(this.table.getValueAt(row,0).toString())).editar();
                }
                else if(this.mode == "estante"){
                    Estante.getEstante(Integer.parseInt(this.table.getValueAt(row,0).toString())).editar();
                }
                else{
                    Emprestimo.getEmprestimo(Integer.parseInt(this.table.getValueAt(row,0).toString())).editar();
                }
            }
        }
        else if(itemText.equals("Atualizar")){
            this.atualizar();
        }
        else if(itemText.equals("Apagar")){
            int row = this.table.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(null,"Selecione uma linha na tabela");
            }
            else{
                if(this.mode == "livro"){
                    Livro.getLivro(Integer.parseInt(this.table.getValueAt(row,0).toString())).excluir();
                }
                else if(this.mode == "estante"){
                    Estante.getEstante(Integer.parseInt(this.table.getValueAt(row,0).toString())).excluir();
                }
                else{
                    Emprestimo.getEmprestimo(Integer.parseInt(this.table.getValueAt(row,0).toString())).excluir();
                }
            }
        }
        else if(itemText.substring(0,3).equals("Nov")){
            if(this.mode == "livro"){
                Livro livro = new Livro();
                livro.cadastrar();
            }
            else if(this.mode == "estante"){
                Estante estante = new Estante();
                estante.cadastrar();
            }
            else{
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.cadastrar();
            }
        }
        else if(itemText.equals("Solicitar Empréstimo")){
            Emprestimo.emprestar();
        }
        else if(itemText.equals("Devolver Livro")){
            Emprestimo.devolver();
        }
        else if(itemText.equals("Fechar")){
            this.window.setVisible(false);
        }
        else if(itemText.equals("Sobre")){
            JFrame aboutWindow = new JFrame("Sobre");
            aboutWindow.setLayout(null);
            aboutWindow.setSize(400,450);
            aboutWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            JTextArea texto = new JTextArea("Dedico este software exclusivamente aos meus familiares - que me \napoiaram para a criação do mesmo - E à Deus, que me deu forças para que eu pudesse criá-lo de maneira competente. Agradeço também à \nvocê pelo uso deste software, que será liberado em breve sob código livre no endereço http://fjorgemota.github.com/ . \nSoftware criado por Fernando Jorge Mota\nPara o Curso Técnico Articulado em Informática",60,400);
            texto.setBounds(10, 10,400, 450);
            texto.setEditable(false);
            texto.setLineWrap(true);
            aboutWindow.add(texto);
            aboutWindow.setResizable(false);
            aboutWindow.setVisible(true);
        }
            
    }  
}
