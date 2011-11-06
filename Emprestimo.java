import java.lang.Integer;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.List;
import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
/**
 * Write a description of class Emprestimo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Emprestimo implements ActionListener
{
    // instance variables - replace the example below with your own
    private int id;
    private String nome;
    private String telefone;
    private Livro livro;
    private int devolvido;
    private String mode;
    private JFrame window;
    private JTextField campoNome;
    private JTextField campoTelefone;
    private JComboBox campoLivro;
    private JCheckBox campoDevolvido;
    private static MySQL mysql = new MySQL();

    /**
     * Constructor for objects of class Emprestimo
     */
    public Emprestimo()
    {
        // initialise instance variables
        this.id = -1;
        this.nome = "";
        this.telefone = "";
        this.livro = null;
        this.devolvido = 0;
        
    }
    public Emprestimo(int id, String nome, String telefone, Livro livro)
    {
        // initialise instance variables
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.livro = livro;
        this.devolvido = 0;
    }
    public Emprestimo(int id, String nome, String telefone, int livro)
    {
        // initialise instance variables
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.livro = Livro.getLivro(livro);
        this.devolvido = 0;
    }
    public Emprestimo(int id, String nome, String telefone, String livro)
    {
        // initialise instance variables
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.livro = Livro.getLivro(livro);
        this.devolvido = 0;
        
    }
    public Emprestimo(String id, String nome, String telefone, Livro livro)
    {
        this.id = Integer.parseInt(id);
        this.nome = nome;
        this.telefone = telefone;
        this.livro = livro;
        this.devolvido = 0;
    }
    public Emprestimo(String id, String nome, String telefone, int livro)
    {
        this.id = Integer.parseInt(id);
        this.nome = nome;
        this.telefone = telefone;
        this.livro = Livro.getLivro(livro);
        this.devolvido = 0;
    }
    public Emprestimo(String id, String nome, String telefone, String livro)
    {
        this.id = Integer.parseInt(id);
        this.nome = nome;
        this.telefone = telefone;
        this.livro = Livro.getLivro(livro);
        this.devolvido = 0;
    }
    public int getID(){
        return this.id;
    }
    public void setID(int id){
        if(this.id == -1){
            this.id = id;
        }
    }
    public void setID(String id){
        if(this.id == -1){
            this.id = Integer.parseInt(id);
        }
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getTelefone(){
        return this.telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public Livro getLivro(){
        return this.livro;
    }
    public void setLivro(Livro livro){
        this.livro = livro;
    }
    public void setLivro(String livro){
        this.livro = Livro.getLivro(livro);
    }
    public void setLivro(int livro){
        this.livro = Livro.getLivro(livro);
    }
    public int getDevolvido(){
        return this.devolvido;
    }
    public boolean isDevolvido(){
        return this.devolvido == 1;
    }
    public void setDevolvido(boolean devolvido){
        if(devolvido==true){
            this.devolvido = 1;
        }
        else{
            this.devolvido = 0;
        }
    }
    public void setDevolvido(int devolvido){
        if(devolvido==0){
            this.devolvido = 0;
        }
        else{
            this.devolvido = 1;
        }
    }
    public void setDevolvido(String devolvido){
        if(devolvido=="0"){
            this.devolvido = 0;
        }
        else{
            this.devolvido = 1;
        }
    }
    public JFrame editar(){
        if(this.mode != null){
            return null;
        }
        this.mode = "editar";
        this.window = new JFrame("Editar Empréstimo do Livro "+this.getLivro().getTitulo()+" para "+this.getNome());
        this.window.setLayout(null);
        this.window.setSize(400,450);
        this.window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //Campo ID:
        JLabel rotuloID = new JLabel("ID:");
        rotuloID.setBounds(10, 10, 100, 30);
        JTextField campoID = new JTextField(""+this.getID());
        campoID.setEditable(false);
        campoID.setBounds(120, 10, 200, 30);
        
        //Campo Titulo
        JLabel rotuloLivro = new JLabel("Livro:");
        rotuloLivro.setBounds(10, 50, 100, 30);
        this.campoLivro= new JComboBox();
        this.campoLivro.setBounds(120, 50, 200, 30);
        List<Livro> livros = Livro.getLivros();
        for(int c=0;c<livros.size();c++){
            Livro livro = livros.get(c);
            this.campoLivro.addItem(livro);
        }
        //Campo Autor
        JLabel rotuloNome = new JLabel("Nome:");
        rotuloNome.setBounds(10, 90, 100, 30);
        this.campoNome = new JTextField(this.getNome());
        this.campoNome.setBounds(120, 90, 200, 30);
        
         //Campo Telefone
        JLabel rotuloTelefone = new JLabel("Telefone: ");
        rotuloTelefone.setBounds(10,130,100,30);
        MaskFormatter telFormat = null;
        try{
            telFormat = new MaskFormatter("(##)####-####");
            
        }
        catch(ParseException e){ 
        }
        this.campoTelefone = new JFormattedTextField(telFormat);
        this.campoTelefone.setBounds(120,130,200,30);
        this.campoTelefone.setText(this.getTelefone());
        JLabel rotuloDevolvido = new JLabel("Foi devolvido?");
        rotuloDevolvido.setBounds(10, 170, 100, 30);
        this.campoDevolvido = new JCheckBox();
        this.campoDevolvido.setSelected(this.isDevolvido());
        this.campoDevolvido.setBounds(120, 170, 200, 30);
        
        JButton botaoEmprestar = new JButton("Editar");
        botaoEmprestar.setBounds(10,210,100,30);
        botaoEmprestar.addActionListener(this);
        JButton botaoCancelar = new JButton("Excluir");
        botaoCancelar.setBounds(120,210,200,30);
        botaoCancelar.addActionListener(this);
        this.window.add(rotuloID);
        this.window.add(campoID);
        this.window.add(rotuloLivro);
        this.window.add(this.campoLivro);
        this.window.add(rotuloNome);
        this.window.add(this.campoNome);
        this.window.add(rotuloNome);
        this.window.add(this.campoTelefone);
        this.window.add(rotuloDevolvido);
        this.window.add(this.campoDevolvido);
        this.window.add(botaoEmprestar);
        this.window.add(botaoCancelar);
        this.window.setVisible(true);
        return this.window;
    }
    public JFrame cadastrar(){
        if(this.mode != null){
            return null;
        }
        this.mode = "cadastrar";
        this.window = new JFrame("Cadastrar Empréstimo");
        this.window.setLayout(null);
        this.window.setSize(400,450);
        this.window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        //Campo Titulo
        JLabel rotuloLivro = new JLabel("Livro:");
        rotuloLivro.setBounds(10, 10, 100, 30);
        this.campoLivro= new JComboBox();
        this.campoLivro.setBounds(120, 10, 200, 30);
        List<Livro> livros = Livro.getLivros();
        for(int c=0;c<livros.size();c++){
            Livro livro = livros.get(c);
            this.campoLivro.addItem(livro);
        }
        this.campoLivro.setSelectedItem(this.getLivro());
        //Campo Autor
        JLabel rotuloNome = new JLabel("Nome:");
        rotuloNome.setBounds(10, 50, 100, 30);
        this.campoNome = new JTextField(this.getNome());
        this.campoNome.setBounds(120, 50, 200, 30);
        
         //Campo Telefone
        JLabel rotuloTelefone = new JLabel("Telefone: ");
        rotuloTelefone.setBounds(10,90,100,30);
        MaskFormatter telFormat = null;
        try{
            telFormat = new MaskFormatter("(##)####-####");
            
        }
        catch(ParseException e){ 
        }
        this.campoTelefone = new JFormattedTextField(telFormat);
        this.campoTelefone.setBounds(120,90,200,30);
        JLabel rotuloDevolvido = new JLabel("Foi devolvido?");
        rotuloDevolvido.setBounds(10, 130, 100, 30);
        this.campoDevolvido = new JCheckBox();
        this.campoDevolvido.setBounds(120, 130, 200, 30);
        
        JButton botaoEmprestar = new JButton("Cadastrar");
        botaoEmprestar.setBounds(10,170,100,30);
        botaoEmprestar.addActionListener(this);
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(120,170,200,30);
        botaoCancelar.addActionListener(this);
        this.window.add(rotuloLivro);
        this.window.add(this.campoLivro);
        this.window.add(rotuloNome);
        this.window.add(this.campoNome);
        this.window.add(rotuloNome);
        this.window.add(this.campoTelefone);
        this.window.add(rotuloDevolvido);
        this.window.add(this.campoDevolvido);
        this.window.add(botaoEmprestar);
        this.window.add(botaoCancelar);
        this.window.setVisible(true);
        return this.window;
    }
    public static JFrame emprestar(){
        final JFrame window = new JFrame("Solicitar Empréstimo");
        window.setLayout(null);
        window.setSize(400,400);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        //campo livro
        JLabel rotuloLivro = new JLabel("Livro: ");
        rotuloLivro.setBounds(10,10,100,30);
        final JComboBox campoLivro = new JComboBox();
        campoLivro.setBounds(120,10,200,30);
        List<Livro> livros = Livro.getLivros();
        for(int c=0;c<livros.size();c++){
            campoLivro.addItem(livros.get(c));
        }
        
        //Campo Nome
        JLabel rotuloNome = new JLabel("Nome: ");
        rotuloNome.setBounds(10,50,100,30);
        final JTextField campoNome = new JTextField();
        campoNome.setBounds(120,50,200,30);
        
        //Campo Telefone
        JLabel rotuloTelefone = new JLabel("Telefone: ");
        rotuloTelefone.setBounds(10,90,100,30);
        MaskFormatter telFormat = null;
        try{
            telFormat = new MaskFormatter("(##)####-####");
            
        }
        catch(ParseException e){ 
        }
        final JTextField campoTelefone = new JFormattedTextField(telFormat);
        campoTelefone.setBounds(120,90,200,30);
        JButton botaoEmprestar = new JButton("Emprestar");
        botaoEmprestar.setBounds(10,130,100,30);
        botaoEmprestar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Livro livro = (Livro)campoLivro.getSelectedItem();
                if(livro.emprestar(campoNome.getText(),campoTelefone.getText())){
                    JOptionPane.showMessageDialog(null,"Livro emprestado com sucesso!");
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Houve um erro durante o registro do empréstimo");
                }
                window.setVisible(false);
            }
        });
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(120,130,200,30);
        botaoCancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                window.setVisible(false);
            }
        });
        
        window.add(rotuloLivro);
        window.add(campoLivro);
        window.add(rotuloNome);
        window.add(campoNome);
        window.add(rotuloTelefone);
        window.add(campoTelefone);
        window.add(botaoEmprestar);
        window.add(botaoCancelar);
        window.setVisible(true);
        return window;
    }
    public static JFrame devolver(){
        final JFrame window = new JFrame("Devolver Livro");
        window.setLayout(null);
        window.setSize(400,400);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        //campo livro
        JLabel rotuloLivro = new JLabel("Livro: ");
        rotuloLivro.setBounds(10,10,100,30);
        final JComboBox campoLivro = new JComboBox();
        campoLivro.setBounds(120,10,200,30);
        List<Livro> livros = Livro.getLivros();
        for(int c=0;c<livros.size();c++){
            campoLivro.addItem(livros.get(c));
        }
        
        //Campo Nome
        JLabel rotuloNome = new JLabel("Nome: ");
        rotuloNome.setBounds(10,50,100,30);
        final JTextField campoNome = new JTextField();
        campoNome.setBounds(120,50,200,30);
        
        //Campo Telefone
        JLabel rotuloTelefone = new JLabel("Telefone: ");
        rotuloTelefone.setBounds(10,90,100,30);
        MaskFormatter telFormat = null;
        try{
            telFormat = new MaskFormatter("(##)####-####");
            
        }
        catch(ParseException e){ 
        }
        final JTextField campoTelefone = new JFormattedTextField(telFormat);
        campoTelefone.setBounds(120,90,200,30);
        JButton botaoEmprestar = new JButton("Emprestar");
        botaoEmprestar.setBounds(10,130,100,30);
        botaoEmprestar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Livro livro = (Livro)campoLivro.getSelectedItem();
                if(livro.devolver(campoNome.getText(),campoTelefone.getText())){
                    JOptionPane.showMessageDialog(null,"Livro devolvido com sucesso!");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Houve um erro durante a atualização do empréstimo do livro!");
                }
            }
        });
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(120,130,200,30);
        botaoCancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                window.setVisible(false);
            }
        });
        
        window.add(rotuloLivro);
        window.add(campoLivro);
        window.add(rotuloNome);
        window.add(campoNome);
        window.add(rotuloTelefone);
        window.add(campoTelefone);
        window.add(botaoEmprestar);
        window.add(botaoCancelar);
        window.setVisible(true);
        return window;
    }
    
    public boolean insere()
    {
        boolean deuCerto = false;
        if(this.getID()==-1){
            if(this.getLivro() == null){
                JOptionPane.showMessageDialog(null,"Informe um livro atraves do metodo setLivro!");
            }
            String sql = "INSERT INTO emprestimo(nome,telefone,Livro_id) VALUES(\""+this.getNome()+"\",\""+this.getTelefone()+"\","+this.getLivro().getID()+");";
            deuCerto = mysql.executaInsert(sql);
            if(deuCerto){
                sql = "SELECT id FROM emprestimo WHERE nome=\""+this.getNome()+"\" AND telefone=\""+this.getTelefone()+"\" AND Livro_id=\""+this.getLivro().getID()+"\" ORDER BY id DESC LIMIT 1;";
                ConjuntoResultados lista = mysql.executaSelect(sql);
                lista.next();
                this.setID(lista.getString("id"));
            }
        }
        return deuCerto;
    }
    public boolean atualiza(){
        boolean deuCerto = false;
        if(this.getID() != -1){
            if(this.getLivro() == null){
                JOptionPane.showMessageDialog(null,"Informe um livro atraves do metodo setLivro!");
            }
            String sql = "UPDATE emprestimo SET nome=\""+this.getNome()+"\", telefone=\""+this.getTelefone()+"\", Livro_id=\""+this.getLivro().getID()+"\" WHERE id="+this.getID()+";";
            deuCerto = mysql.executaUpdate(sql);
        }
        return deuCerto;
    }
    public boolean apaga(){
        boolean deuCerto = false;
        if(this.getID() != -1){
            String sql = "DELETE FROM emprestimo WHERE id="+this.getID()+";";
            deuCerto = mysql.executaDelete(sql);
        }
        return deuCerto;
    }
    public boolean salvar(){
        if(this.id == -1){
            return this.insere();
        }
        else{
            return this.atualiza();
        }
    }
     public void excluir(){
        if(JOptionPane.showConfirmDialog(null,"Confirmação", "Deseja realmente apagar este empréstimo?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            if(this.apaga()){
                JOptionPane.showMessageDialog(null,"Empréstimo apagado com sucesso!");
            }
            else{
                JOptionPane.showMessageDialog(null,"Houve um erro durante o apagamento do empréstimo!");
            }
        }
    }
    public static List<Emprestimo> getEmprestimos(){
        List<Emprestimo> emprestimos = new ArrayList();
        String sql = "SELECT * FROM emprestimo";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        while(lista.next()){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setID(lista.getString("id"));
            emprestimo.setNome(lista.getString("nome"));
            emprestimo.setTelefone(lista.getString("telefone"));
            emprestimo.setDevolvido(lista.getString("devolvido"));
            emprestimo.setLivro(lista.getString("Livro_id"));
            emprestimos.add(emprestimo);
        }
        return emprestimos;
    }
    public static List<Emprestimo> getEmprestimosByLivro(Livro livro){
        List<Emprestimo> emprestimos = new ArrayList();
        String sql = "SELECT * FROM emprestimo WHERE Livro_id="+livro.getID();
        ConjuntoResultados lista = mysql.executaSelect(sql);
        while(lista.next()){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setID(lista.getString("id"));
            emprestimo.setNome(lista.getString("nome"));
            emprestimo.setTelefone(lista.getString("telefone"));
            emprestimo.setDevolvido(lista.getString("devolvido"));
            emprestimo.setLivro(lista.getString("Livro_id"));
            emprestimos.add(emprestimo);
        }
        return emprestimos;
    }
    public static List<Emprestimo> getEmprestimosByLivro(String livro){
        List<Emprestimo> emprestimos = new ArrayList();
        String sql = "SELECT * FROM emprestimo WHERE Livro_id="+livro;
        ConjuntoResultados lista = mysql.executaSelect(sql);
        while(lista.next()){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setID(lista.getString("id"));
            emprestimo.setNome(lista.getString("nome"));
            emprestimo.setTelefone(lista.getString("telefone"));
            emprestimo.setDevolvido(lista.getString("devolvido"));
            emprestimo.setLivro(lista.getString("Livro_id"));
            emprestimos.add(emprestimo);
        }
        return emprestimos;
    }
    public static List<Emprestimo> getEmprestimosByLivro(int livro){
        List<Emprestimo> emprestimos = new ArrayList();
        String sql = "SELECT * FROM emprestimo WHERE Livro_id="+livro;
        ConjuntoResultados lista = mysql.executaSelect(sql);
        while(lista.next()){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setID(lista.getString("id"));
            emprestimo.setNome(lista.getString("nome"));
            emprestimo.setTelefone(lista.getString("telefone"));
            emprestimo.setDevolvido(lista.getString("devolvido"));
            emprestimo.setLivro(lista.getString("Livro_id"));
            emprestimos.add(emprestimo);
        }
        return emprestimos;
    }
    public static Emprestimo getEmprestimo(int id){
        String sql = "SELECT * FROM emprestimo WHERE id="+id+" LIMIT 1";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        if(lista.next()){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setID(lista.getString("id"));
            emprestimo.setNome(lista.getString("nome"));
            emprestimo.setTelefone(lista.getString("telefone"));
            emprestimo.setDevolvido(lista.getString("devolvido"));
            emprestimo.setLivro(lista.getString("Livro_id"));
            return emprestimo;
        }
        else{
            return null;
        }
    }
    public static Emprestimo getEmprestimo(String nome, String telefone, Livro livro){
        String sql = "SELECT * FROM emprestimo WHERE nome=\""+nome+"\" AND telefone=\""+telefone+"\" AND Livro_id="+livro.getID()+" LIMIT 1";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        if(lista.next()){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setID(lista.getString("id"));
            emprestimo.setNome(lista.getString("nome"));
            emprestimo.setTelefone(lista.getString("telefone"));
            emprestimo.setDevolvido(lista.getString("devolvido"));
            emprestimo.setLivro(lista.getString("Livro_id"));
            return emprestimo;
        }
        else{
            return null;
        }
    }
    public static Emprestimo getEmprestimo(String nome, String telefone, int livro){
        String sql = "SELECT * FROM emprestimo WHERE nome=\""+nome+"\" AND telefone=\""+telefone+"\" AND Livro_id="+livro+" LIMIT 1";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        if(lista.next()){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setID(lista.getString("id"));
            emprestimo.setNome(lista.getString("nome"));
            emprestimo.setTelefone(lista.getString("telefone"));
            emprestimo.setDevolvido(lista.getString("devolvido"));
            emprestimo.setLivro(lista.getString("Livro_id"));
            return emprestimo;
        }
        else{
            return null;
        }
    }
     public static Emprestimo getEmprestimo(String nome, String telefone, String livro){
        String sql = "SELECT * FROM emprestimo WHERE nome=\""+nome+"\" AND telefone=\""+telefone+"\" AND Livro_id="+livro+" LIMIT 1";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        if(lista.next()){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setID(lista.getString("id"));
            emprestimo.setNome(lista.getString("nome"));
            emprestimo.setTelefone(lista.getString("telefone"));
            emprestimo.setDevolvido(lista.getString("devolvido"));
            emprestimo.setLivro(lista.getString("Livro_id"));
            return emprestimo;
        }
        else{
            return null;
        }
    }
    public static JTable lista(List<Emprestimo> emprestimos){
        Object[][] lista = new Object[emprestimos.size()][6];
        String[] columns = {"ID","Nome","Telefone","Livro","Estante","Devolvido"};
        for(int c=0;c<emprestimos.size(); c++){
            Emprestimo emprestimo = emprestimos.get(c);
            lista[c][0] = emprestimo.getID();
            lista[c][1] = emprestimo.getNome();
            lista[c][2] = emprestimo.getTelefone();
            lista[c][3] = emprestimo.getLivro().getTitulo();
            
            if(emprestimo.getLivro().getEstante()!=null){
                lista[c][4] = emprestimo.getLivro().getEstante().getNome();
            }
            else{
                lista[c][4] = "Sem Estante definida";
            }
            lista[c][5] = emprestimo.isDevolvido()?"Sim":"Não";
        }
        JTable table = new JTable(lista, columns){  
            public boolean isCellEditable(int row,int column){  
                return false;  
            }  
        };
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        return table;
    }
    public static JTable lista(){
        return lista(getEmprestimos());
    }
    public void actionPerformed(ActionEvent e){
        
        if(this.mode == "cadastrar"){
            //Cadastra
            this.setNome(this.campoNome.getText());
            this.setTelefone(this.campoTelefone.getText());
            this.setLivro((Livro)this.campoLivro.getSelectedItem());
            this.setDevolvido(this.campoDevolvido.isSelected());
            if(this.salvar()){
                JOptionPane.showMessageDialog(null,"Empréstimo Cadastrado com Sucesso!");
            }
            else{
                JOptionPane.showMessageDialog(null,"Houve um erro durante o cadastro do livro");
            }
        }
        else if(this.mode == "editar"){
            JButton botao = (JButton)e.getSource();
            if(botao.getText() == "Editar"){
                this.setNome(this.campoNome.getText());
                this.setTelefone(this.campoTelefone.getText());
                this.setLivro((Livro)this.campoLivro.getSelectedItem());
                this.setDevolvido(this.campoDevolvido.isSelected());
                if(this.salvar()){
                    JOptionPane.showMessageDialog(null,"Empréstimo Editado com Sucesso!");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Houve um erro durante a Edicao do Empréstimo");
                }
            }
            else{
                this.excluir();
            }
        }
        this.mode = null;
        this.window.setVisible(false);
    }
}
