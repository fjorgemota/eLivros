
import java.lang.Integer;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.text.MaskFormatter;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.awt.Dimension;
/*
 * Write a description of class Livro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Livro implements ActionListener
{
    // instance variables - replace the example below with your own
    private int id;
    private String titulo;
    private String autor;
    private String descricao;
    private Date anoLancamento;
    private Estante estante;
    private DateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat _dateFormatDisplay = new SimpleDateFormat("dd/MM/yyyy");
    private boolean _autoSave;
    private String mode;
    private JTextField campoID;
    private JTextField campoTitulo;
    private JTextField campoAutor;
    private JTextArea campoDescricao;
    private JDateField campoAnoLancamento;
    private JComboBox campoEstante;
    private JFrame window;
    
    private static MySQL mysql = new MySQL();
    /**
     * Constructor for objects of class Livro
     */
    public Livro()
    {
        // Cria um novo Livro
        this.id = -1;
        this.titulo = "Sem Titulo";
        this.autor = "Sem Autor";
        this.descricao = "Sem Descricao";
        this.anoLancamento = new Date();
        this.estante = null;
        this._autoSave = false;
        this.mode = null;
    }
    
    public Livro(int id,String titulo, String autor, String descricao, Date anoLancamento,Estante estante){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
        this.anoLancamento = anoLancamento;
        this.estante = estante;
        this._autoSave = false;  
        this.mode = null;
    }
    public Livro(int id,String titulo, String autor, String descricao, Date anoLancamento,int estante){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
        this.anoLancamento = anoLancamento;
        this.estante = Estante.getEstante(estante);
        this._autoSave = false;  
        this.mode = null;
    }
    public void setID(int id){
        if(this.id==-1){
            this.id = id;
        }
    }
    public void setID(String id){
        if(this.id==-1){
            this.id = Integer.parseInt(id);
        }
    }
    
    public int getID(){
        return this.id;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public void setAutor(String autor){
        this.autor = autor;
    }
    
    public String getAutor(){
        return this.autor;
    }
    
    public void setAnoLancamento(String anoLancamento){
        try
        {
            this.anoLancamento = this._dateFormat.parse(anoLancamento);
        }
        catch(ParseException e){
            this.anoLancamento = new Date();
        }
    }
    
    public void setAnoLancamento(Date anoLancamento){
        this.anoLancamento = anoLancamento;
    }
    
    public Date getAnoLancamento(){
        return this.anoLancamento;
    }
    public String getAnoLancamentoDisplay(){
        return this._dateFormatDisplay.format(this.anoLancamento);
    }
    public String getAnoLancamentoString(){
        return this._dateFormat.format(this.anoLancamento);
    }
    public void setEstante(Estante estante){
        this.estante = estante;
    }
    public void setEstante(int estante){
        this.estante = Estante.getEstante(estante);
    }
    public void setEstante(String estante){
        if(estante.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Informe uma estante válida!");
        }
        else{
            this.estante = Estante.getEstante(Integer.parseInt(estante));
        }
    }
    public Estante getEstante(){
        return this.estante;
    }
    public boolean insere()
    {
        // put your code here
        boolean deuCerto = false;
        if(this.getID()==-1){ 
            //Inserindo
            if(this.getEstante() == null){
                JOptionPane.showMessageDialog(null,"Informe uma estante através do método setEstante!");
            }
            String sql = "INSERT INTO livros (titulo, autor, descricao, anoLancamento, Estante_id) VALUES (\""+this.getTitulo()+"\",\""+this.getAutor()+"\",\""+this.getDescricao()+"\",\""+this.getAnoLancamentoString()+"\", "+this.getEstante().getID()+");";
            deuCerto = mysql.executaInsert(sql);
            if(deuCerto){
                sql = "SELECT id FROM livros WHERE titulo=\""+this.getTitulo()+"\" AND autor=\""+this.getAutor()+"\" AND descricao=\""+this.getDescricao()+"\" AND anoLancamento=\""+this.getAnoLancamentoString()+"\" AND Estante_id="+this.getEstante().getID()+" ORDER BY id DESC LIMIT 1;";
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
            if(this.getEstante() == null){
                JOptionPane.showMessageDialog(null,"Informe uma estante através do método setEstante!");
            }
            String sql = "UPDATE livros SET titulo=\""+this.getTitulo()+"\", autor=\""+this.getAutor()+"\", descricao=\""+this.getDescricao()+"\", anoLancamento=\""+this.getAnoLancamentoString()+"\", Estante_id="+this.getEstante().getID()+" WHERE id="+this.getID()+";";
            deuCerto = mysql.executaUpdate(sql);
        }
        return deuCerto;
    }
    public boolean apaga(){
        boolean deuCerto = false;
        if(this.getID() != -1){
            String sql = "DELETE FROM emprestimos WHERE Livro_id="+this.getID()+";";
            deuCerto = mysql.executaDelete(sql);
            if(deuCerto){
                sql = "DELETE FROM livros WHERE id="+this.getID()+";";
                deuCerto = mysql.executaDelete(sql);
            }
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
    public JFrame editar(){
        if(this.mode != null){
            return null;
        }
        this.mode = "editar";
        this.window = new JFrame("Editar Livro "+this.getTitulo());
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
        JLabel rotuloTitulo = new JLabel("Titulo:");
        rotuloTitulo.setBounds(10, 50, 100, 30);
        this.campoTitulo = new JTextField(this.getTitulo());
        this.campoTitulo.setBounds(120, 50, 200, 30);
        
        //Campo Autor
        JLabel rotuloAutor = new JLabel("Autor:");
        rotuloAutor.setBounds(10, 90, 100, 30);
        this.campoAutor = new JTextField(this.getAutor());
        this.campoAutor.setBounds(120, 90, 200, 30);
        
        //Campo Descricao
        JLabel rotuloDescricao = new JLabel("Descricao:");
        rotuloDescricao.setBounds(10, 120, 100, 90);
        this.campoDescricao = new JTextArea(this.getDescricao(),3,200);
        this.campoDescricao.setBounds(120, 120, 200, 90);
        
        //Campo Ano Lancamento
        JLabel rotuloAnoLancamento = new JLabel("LanÃ§amento:");
        rotuloAnoLancamento.setBounds(10, 220, 100, 30);
        this.campoAnoLancamento = new JDateField(this.getAnoLancamento(),this._dateFormat);
        this.campoAnoLancamento.setBounds(120, 220, 200, 30);
        
        //Campo Estante
        JLabel rotuloEstante = new JLabel("Estante:");
        rotuloEstante.setBounds(10, 260, 100, 30);
        this.campoEstante = new JComboBox();
        this.campoEstante.setBounds(120, 260, 200, 30);
        Iterator<Estante> estantes = Estante.getEstantes().iterator();
        while(estantes.hasNext()){
            this.campoEstante.addItem(estantes.next());
        }
        this.campoEstante.setSelectedItem(this.getEstante());
        JButton botaoEditar = new JButton("Editar");
        botaoEditar.setBounds(10,300,100,30);
        botaoEditar.addActionListener(this);
        JButton botaoApagar = new JButton("Apagar");
        botaoApagar.setBounds(120,300,200,30);
        botaoApagar.addActionListener(this);
        this.window.add(rotuloID);
        this.window.add(campoID);
        this.window.add(rotuloTitulo);
        this.window.add(this.campoTitulo);        
        this.window.add(rotuloAutor);
        this.window.add(this.campoAutor);
        this.window.add(rotuloDescricao);
        this.window.add(this.campoDescricao);
        this.window.add(rotuloAnoLancamento);
        this.window.add(this.campoAnoLancamento);
        this.window.add(rotuloEstante);
        this.window.add(this.campoEstante);
        this.window.add(botaoEditar);
        this.window.add(botaoApagar);
        this.window.setVisible(true);
        return this.window;
    }     
    public boolean emprestar(String nome, String telefone){
        this.salvar();
        Emprestimo emprestimo = new Emprestimo(); 
        emprestimo.setNome(nome);
        emprestimo.setTelefone(telefone);
        emprestimo.setLivro(this);
        return emprestimo.salvar();
    }
    public boolean devolver(String nome, String telefone){
        this.salvar();
        Emprestimo emprestimo = Emprestimo.getEmprestimo(nome,telefone,this);
        if(emprestimo != null){
            emprestimo.setDevolvido(true);
            return emprestimo.salvar();
        }
        return false;
    }
    public String toString(){
        return this.getTitulo()+" - "+this.getID();
    }
        
    public JFrame cadastrar(){
        if(this.mode != null || this.id != -1){
            return null;
        }
        this.mode = "cadastrar";
        this.window = new JFrame("Cadastrar Livro ");
        this.window.setLayout(null);
        this.window.setSize(400,400);
        this.window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        //Campo Titulo
        JLabel rotuloTitulo = new JLabel("Titulo:");
        rotuloTitulo.setBounds(10, 10, 100, 30);
        this.campoTitulo = new JTextField();
        this.campoTitulo.setBounds(120, 10, 200, 30);
        
        //Campo Autor
        JLabel rotuloAutor = new JLabel("Autor:");
        rotuloAutor.setBounds(10, 50, 100, 30);
        this.campoAutor = new JTextField();
        this.campoAutor.setBounds(120, 50, 200, 30);
        
        //Campo Descricao
        JLabel rotuloDescricao = new JLabel("Descrição:");
        rotuloDescricao.setBounds(10, 90, 100, 90);
        this.campoDescricao = new JTextArea(3,200);
        this.campoDescricao.setBounds(120, 90, 200, 90);
        
        //Campo Ano Lancamento
        JLabel rotuloAnoLancamento = new JLabel("Lançamento:");
        rotuloAnoLancamento.setBounds(10, 190, 100, 30);
        this.campoAnoLancamento = new JDateField(this.getAnoLancamento(),this._dateFormat);
        this.campoAnoLancamento.setBounds(120, 190, 200, 30);
        
        //Campo Estante
        JLabel rotuloEstante = new JLabel("Estante:");
        rotuloEstante.setBounds(10, 230, 100, 30);
        this.campoEstante = new JComboBox();
        this.campoEstante.setBounds(120, 230, 200, 30);
        Iterator estantes = Estante.getEstantes().iterator();
        while(estantes.hasNext()){
            this.campoEstante.addItem(estantes.next());
        }
        
        JButton botao = new JButton("Cadastrar");
        botao.setBounds(10,270,200,30);
        botao.addActionListener(this);
        this.window.add(rotuloTitulo);
        this.window.add(this.campoTitulo);        
        this.window.add(rotuloAutor);
        this.window.add(this.campoAutor);
        this.window.add(rotuloDescricao);
        this.window.add(this.campoDescricao);
        this.window.add(rotuloAnoLancamento);
        this.window.add(this.campoAnoLancamento);
        this.window.add(rotuloEstante);
        this.window.add(this.campoEstante);
        this.window.add(botao);
        this.window.setVisible(true);
        return this.window;
    }          
    public void excluir(){
        if(JOptionPane.showConfirmDialog(null,"Deseja realmente apagar este livro?", "Deseja realmente apagar este livro?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            if(this.apaga()){
                JOptionPane.showMessageDialog(null,"Livro apagado com sucesso!");
            }
            else{
                JOptionPane.showMessageDialog(null,"Houve um erro durante o apagamento do livro!");
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(this.mode == "cadastrar"){
            //Cadastra
            this.setTitulo(this.campoTitulo.getText());
            this.setAutor(this.campoAutor.getText());
            this.setDescricao(this.campoDescricao.getText());
            this.setAnoLancamento(this.campoAnoLancamento.getDate());
            this.setEstante((Estante)this.campoEstante.getSelectedItem());
            if(this.salvar()){
                JOptionPane.showMessageDialog(null,"Livro Cadastrado com Sucesso!");
            }
            else{
                JOptionPane.showMessageDialog(null,"Houve um erro durante o cadastro do livro");
            }
        }
        else if(this.mode == "editar"){
            JButton botao = (JButton)e.getSource();
            if(botao.getText() == "Editar"){
                this.setTitulo(this.campoTitulo.getText());
                this.setAutor(this.campoAutor.getText());
                this.setDescricao(this.campoDescricao.getText());
                this.setAnoLancamento(this.campoAnoLancamento.getDate());
                this.setEstante((Estante)this.campoEstante.getSelectedItem());
                if(this.salvar()){
                    JOptionPane.showMessageDialog(null,"Livro Editado com Sucesso!");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Houve um erro durante a Edicao do livro");
                }
            }
            else{
                this.excluir();
            }
        }
        this.mode = null;
        this.window.setVisible(false);
    }
            
    public static List<Livro> getLivros(){
        List<Livro> livros = new ArrayList();
        String sql = "SELECT * FROM livros";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        while(lista.next()){
            Livro livro = new Livro();
            livro.setID(lista.getString("id"));
            livro.setTitulo(lista.getString("titulo"));
            livro.setDescricao(lista.getString("descricao"));
            livro.setAutor(lista.getString("autor"));
            livro.setAnoLancamento(lista.getString("anoLancamento"));
            livro.setEstante(lista.getString("Estante_id"));
            livros.add(livro);
        }
        return livros;
    }
    
    public static Livro getLivro(int id){
        String sql = "SELECT * FROM livros WHERE id="+id+" LIMIT 1";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        if(lista.next()){
            Livro livro = new Livro();
            livro.setID(lista.getString("id"));
            livro.setTitulo(lista.getString("titulo"));
            livro.setDescricao(lista.getString("descricao"));
            livro.setAutor(lista.getString("autor"));
            livro.setAnoLancamento(lista.getString("anoLancamento"));
            livro.setEstante(lista.getString("Estante_id"));
            return livro;
        }
        else{
            return null;
        }
    }
    public static Livro getLivro(String id){
        String sql = "SELECT * FROM livros WHERE id="+id+" LIMIT 1";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        if(lista.next()){
            Livro livro = new Livro();
            livro.setID(lista.getString("id"));
            livro.setTitulo(lista.getString("titulo"));
            livro.setDescricao(lista.getString("descricao"));
            livro.setAutor(lista.getString("autor"));
            livro.setAnoLancamento(lista.getString("anoLancamento"));
            livro.setEstante(lista.getString("Estante_id"));
            return livro;
        }
        else{
            return null;
        }
    }
    public static JTable lista(List<Livro> livros){
        Object[][] lista = new Object[livros.size()][6];
        String[] columns = {"ID","Titulo","Descricao","Autor","Lancamento","Estante"};
        for(int c=0;c<livros.size(); c++){
            Livro livro = livros.get(c);
            lista[c][0] = livro.getID();
            lista[c][1] = livro.getTitulo();
            lista[c][2] = livro.getDescricao();
            lista[c][3] = livro.getAutor();
            lista[c][4] = livro.getAnoLancamentoDisplay();
            if(livro.getEstante()!=null){
                lista[c][5] = livro.getEstante().getNome();
            }
            else{
                lista[c][5] = "Sem Estante definida";
            }
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
    
    public static List<Livro> procurar(String id, String titulo, String descricao, String autor, Estante estante){
        List<Livro> livros = new ArrayList();
        String sql = "SELECT * FROM livros WHERE id LIKE \""+id+"\" AND titulo LIKE \""+titulo+"\" AND descricao LIKE \""+descricao+"\" AND autor LIKE \""+autor+"\"";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        while(lista.next()){
            Livro livro = new Livro();
            livro.setID(lista.getString("id"));
            livro.setTitulo(lista.getString("titulo"));
            livro.setDescricao(lista.getString("descricao"));
            livro.setAutor(lista.getString("autor"));
            livro.setAnoLancamento(lista.getString("anoLancamento"));
            livro.setEstante(lista.getString("Estante_id"));
            livros.add(livro);
        }
        return livros;
    }
     public static List<Livro> getLivrosByEstante(Estante estante){
        List<Livro> livros = new ArrayList();
        String sql = "SELECT * FROM livros WHERE Estante_id=\""+estante.getID()+"\"";
        ConjuntoResultados lista = mysql.executaSelect(sql);
        while(lista.next()){
            Livro livro = new Livro();
            livro.setID(lista.getString("id"));
            livro.setTitulo(lista.getString("titulo"));
            livro.setDescricao(lista.getString("descricao"));
            livro.setAutor(lista.getString("autor"));
            livro.setAnoLancamento(lista.getString("anoLancamento"));
            livro.setEstante(lista.getString("Estante_id"));
            livros.add(livro);
        }
        return livros;
    }
    public static JTable lista(){
        return lista(getLivros());
    }   
}
