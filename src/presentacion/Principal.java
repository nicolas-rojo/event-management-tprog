package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Asistente;
import logica.EdicionEvento;
import logica.Evento;
import logica.Fabrica;
import logica.TipoRegistro;
import logica.ctrlmanejador.ManejadorEvento;
import logica.ctrlmanejador.ManejadorUsuario;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTRegistro;
import logica.interfaces.IEventos;
import logica.interfaces.IUsuario;

import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Principal {

    private JFrame frmGestionDeUsuarios;
    private IUsuario ICU;
    private IEventos IEV;
    private CrearUsuario creUsrInternalFrame;
    private ConsultaUsuario lisUsrInternalFrame;
    private ModificarUsuario modUsrInternalFrame;
    private CrearEvento creEventoInternalFrame;
    private CrearTipoRegistro creTRegistroInternalFrame;
    private ConsultaTipoRegistro consuTRegistroInternalFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                   /**crear instancias para testing**/
        
//                    Asistente a1 = new Asistente("Luca", "luk", "scaboni.luca@gmail.com", "Scaboni", LocalDate.now());
//
//                    Evento e1 = new Evento(new DataEvento("evento1", "e1", LocalDate.now(), "desc1"));
//                    Evento e2 = new Evento(new DataEvento("evento2", "e2", LocalDate.now(), "desc2"));
//
//                    EdicionEvento ed11 = new EdicionEvento(new DataEdicion("evento1 ed1", "e1ed1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "ciudad", "pais"));
//                    EdicionEvento ed21 = new EdicionEvento(new DataEdicion("evento2 ed2", "e2ed1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "ciudad", "pais"));
//                    EdicionEvento ed22 = new EdicionEvento(new DataEdicion("evento2 ed2", "e2ed2", LocalDate.now(), LocalDate.now(), LocalDate.now(), "ciudad", "pais"));
//                    
//              
//                    TipoRegistro tr1 = new TipoRegistro(new DataTRegistro("Generales", "asodijansoia", 20, 123));
//                    TipoRegistro tr2 = new TipoRegistro(new DataTRegistro("Estudiantes", "Registro con descuento para estudiantes", 15, 200));
//                    TipoRegistro tr3 = new TipoRegistro(new DataTRegistro("VIP", "Acceso con beneficios exclusivos", 50, 50));
//                    TipoRegistro tr4 = new TipoRegistro(new DataTRegistro("Profesores", "Registro especial para docentes", 10, 100));
//                    TipoRegistro tr5 = new TipoRegistro(new DataTRegistro("Internacionales", "Registro para participantes del exterior", 40, 80));
//                    TipoRegistro tr6 = new TipoRegistro(new DataTRegistro("Virtuales", "Registro para modalidad online", 5, 500));
//                    TipoRegistro tr7 = new TipoRegistro(new DataTRegistro("Empresariales", "Registro corporativo para empresas", 100, 30));
//                    TipoRegistro tr8 = new TipoRegistro(new DataTRegistro("Invitados", "Registro gratuito para invitados especiales", 0, 1234));
//                    
//                    ed22.agregarTRegistro(tr8);
//                    ed22.agregarTRegistro(tr7);
//                    ed22.agregarTRegistro(tr6);
//                    ed22.agregarTRegistro(tr5);
//                    ed11.agregarTRegistro(tr4);
//                    ed11.agregarTRegistro(tr3);
//                    ed11.agregarTRegistro(tr2);
//                    ed11.agregarTRegistro(tr1);
//                    
//
                     ManejadorEvento me = ManejadorEvento.getInstance();
//                    me.addEvento(e1);
//                    me.addEvento(e2);
//
//                    ManejadorUsuario mu = ManejadorUsuario.getinstance();
//                    mu.addUsuario(a1);
//
//                    e1.agregarEdicion(ed11);
//                    e2.agregarEdicion(ed21);
//                    e2.agregarEdicion(ed22);
                    
                    Evento EV01 = new Evento(new DataEvento("Conferencia de Tecnologia", "CONFTEC", LocalDate.now(), "Evento sobre innovaci´on tecnol´ogica"));
                    Evento EV02 = new Evento(new DataEvento("Feria del Libro", "FERLIB", LocalDate.now(), "Encuentro anual de literatura"));
                    Evento EV03 = new Evento(new DataEvento("Montevideo Rock", "MONROCK", LocalDate.now(), "Festival de rock con artistas nacionales e internacionales"));
                    Evento EV04 = new Evento(new DataEvento("Marat´on de Montevideo", "MARATON", LocalDate.now(), "Competencia deportiva anual en la capital"));
                    Evento EV05 = new Evento(new DataEvento("Montevideo Comics", "COMICS", LocalDate.now(), "Convenci´on de historietas, cine y cultura geek"));
                    Evento EV06 = new Evento(new DataEvento("Expointer Uruguay", "EXPOAGRO", LocalDate.now(), "Exposici´on internacional agropecuaria y ganadera"));
                    Evento EV07 = new Evento(new DataEvento("Montevideo Fashion Week", "MFASHION", LocalDate.now(), "Pasarela de moda uruguaya e internacional"));


                    EdicionEvento EDEV01 = new EdicionEvento(new DataEdicion("Montevideo Rock 2025", "MONROCK25", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Montevideo", "Uruguay"));
                    EdicionEvento EDEV02 = new EdicionEvento(new DataEdicion("Marat´on de Montevideo 2025", "MARATON25", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Montevideo", "Uruguay"));
                    EdicionEvento EDEV03 = new EdicionEvento(new DataEdicion("Marat´on de Montevideo 2024", "MARATON24", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Montevideo", "Uruguay"));
                    EdicionEvento EDEV04 = new EdicionEvento(new DataEdicion("Marat´on de Montevideo 2022", "MARATON22", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Montevideo", "Uruguay"));
                    EdicionEvento EDEV05 = new EdicionEvento(new DataEdicion("Montevideo Comics 2024", "COMICS24", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Montevideo", "Uruguay"));
                    EdicionEvento EDEV06 = new EdicionEvento(new DataEdicion("Montevideo Comics 2025", "COMICS25", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Montevideo", "Uruguay"));
                    EdicionEvento EDEV07 = new EdicionEvento(new DataEdicion("Expointer Uruguay 2025", "EXPOAGRO25", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Durazno ", "Uruguay"));
                    EdicionEvento EDEV08 = new EdicionEvento(new DataEdicion("Tecnolog´ıa Punta del Este 2026", "CONFTECH26", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Punta del Este", "Uruguay"));
                    EdicionEvento EDEV09 = new EdicionEvento(new DataEdicion("Mobile World Congress 2025", "MWC", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Barcelona", "Espa˜na"));
                    EdicionEvento EDEV10 = new EdicionEvento(new DataEdicion("Web Summit 2026", "WS26", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Lisboa", "Portugal"));


                    EV01.agregarEdicion(EDEV08);
                    EV01.agregarEdicion(EDEV09);
                    EV01.agregarEdicion(EDEV10);

                    EV04.agregarEdicion(EDEV02);
                    EV04.agregarEdicion(EDEV03);
                    EV04.agregarEdicion(EDEV04);

                    EV05.agregarEdicion(EDEV05);
                    EV05.agregarEdicion(EDEV06);

                    EV03.agregarEdicion(EDEV01);

                    EV06.agregarEdicion(EDEV07);



                    TipoRegistro TR01 = new TipoRegistro(new DataTRegistro("General","Acceso general a MontevideoRock (2 d´ıas)", 500, 400));
                    TipoRegistro TR02 = new TipoRegistro(new DataTRegistro("VIP","Incluye backstage + acceso preferencial", 1500, 2000));
                    TipoRegistro TR03 = new TipoRegistro(new DataTRegistro("Corredor 42k","Inscripci´on a la marat´on completa", 250, 400 ));
                    TipoRegistro TR04 = new TipoRegistro(new DataTRegistro("Corredor 21k","Inscripci´on a la media marat´on", 4000, 200 ));
                    TipoRegistro TR05 = new TipoRegistro(new DataTRegistro("Corredor 10K","Inscripci´on a la carrera 10K ", 1200, 499));
                    TipoRegistro TR06 = new TipoRegistro(new DataTRegistro("Corredor 42K","Inscripci´on a la marat´on completa", 800, 700 ));
                    TipoRegistro TR07 = new TipoRegistro(new DataTRegistro("Corredor 21K ","Inscripci´on a la media marat´on", 500, 1000));
                    TipoRegistro TR08 = new TipoRegistro(new DataTRegistro("Corredor 42K","Inscripci´on a la marat´on completa", 1000, 300 ));
                    TipoRegistro TR09 = new TipoRegistro(new DataTRegistro("Corredor 21K","Inscripci´on a la media marat´on", 500, 500 ));
                    TipoRegistro TR10 = new TipoRegistro(new DataTRegistro("Corredor 10K","Inscripci´on a la carrera 10K", 1100, 450 ));
                    TipoRegistro TR11 = new TipoRegistro(new DataTRegistro("General ","Entrada para los 4 d´ıas de Montevideo Comics", 900, 750 ));
                    TipoRegistro TR12 = new TipoRegistro(new DataTRegistro("Cosplayer","Entrada especial con acreditaci´on para concurso cosplay", 650, 1400));
                    TipoRegistro TR13 = new TipoRegistro(new DataTRegistro("General","Entrada para los 4 d´ıas de Montevideo Comics", 600, 1500 ));
                    TipoRegistro TR14 = new TipoRegistro(new DataTRegistro("Cosplayer","Entrada especial con acreditaci´on para concurso cosplay", 300, 50 ));
                    TipoRegistro TR15 = new TipoRegistro(new DataTRegistro("General","Acceso a la exposici´onagropecuaria", 800, 1000 ));
                    TipoRegistro TR16 = new TipoRegistro(new DataTRegistro("Empresarial","Acceso para empresas + networking", 500, 100 ));
                    TipoRegistro TR17 = new TipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 300, 5000 ));
                    TipoRegistro TR18 = new TipoRegistro(new DataTRegistro("General","Acceso general", 2000, 5 ));
                    TipoRegistro TR19 = new TipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 1800, 300 ));
                    TipoRegistro TR20 = new TipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 1500, 500 ));
                    TipoRegistro TR21 = new TipoRegistro(new DataTRegistro("General","Acceso general", 1000, 50 ));
                    TipoRegistro TR22 = new TipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 750, 550 ));
                    TipoRegistro TR23 = new TipoRegistro(new DataTRegistro("Full","Acceso ilimitado + Cena de gala", 900, 30 ));
                    TipoRegistro TR24 = new TipoRegistro(new DataTRegistro("General","Acceso general", 650, 5 ));
                    TipoRegistro TR25 = new TipoRegistro(new DataTRegistro("Estudiante","Acceso para estudiantes", 300, 1 ));



                    EDEV01.agregarTRegistro(TR01);
                    EDEV01.agregarTRegistro(TR02);

                    EDEV02.agregarTRegistro(TR03);
                    EDEV02.agregarTRegistro(TR04);
                    EDEV02.agregarTRegistro(TR05);

                    EDEV03.agregarTRegistro(TR06);
                    EDEV03.agregarTRegistro(TR07);

                    EDEV04.agregarTRegistro(TR08);
                    EDEV04.agregarTRegistro(TR09);
                    EDEV04.agregarTRegistro(TR10);

                    EDEV05.agregarTRegistro(TR11);
                    EDEV05.agregarTRegistro(TR12);

                    EDEV06.agregarTRegistro(TR13);
                    EDEV06.agregarTRegistro(TR14);

                    EDEV07.agregarTRegistro(TR15);
                    EDEV07.agregarTRegistro(TR16);

                    EDEV08.agregarTRegistro(TR17);
                    EDEV08.agregarTRegistro(TR18);
                    EDEV08.agregarTRegistro(TR19);

                    EDEV09.agregarTRegistro(TR20);
                    EDEV09.agregarTRegistro(TR21);
                    EDEV09.agregarTRegistro(TR22);

                    EDEV10.agregarTRegistro(TR23);
                    EDEV10.agregarTRegistro(TR24);
                    EDEV10.agregarTRegistro(TR25);
                    
                    me.addEvento(EV01);
                    me.addEvento(EV02);
                    me.addEvento(EV03);
                    me.addEvento(EV04);
                    me.addEvento(EV05);
                    me.addEvento(EV06);
                    me.addEvento(EV07);
                    
                    
                    window.frmGestionDeUsuarios.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        initialize();

        // Inicialización
        Fabrica fabrica = Fabrica.getInstance();
        ICU = fabrica.getIControladorUsuario();
        IEV = fabrica.getIControladorEventos();
        
        // Se crean los InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecución.
        creUsrInternalFrame = new CrearUsuario(ICU);
        creUsrInternalFrame.setVisible(false);

        lisUsrInternalFrame = new ConsultaUsuario(ICU);
        lisUsrInternalFrame.setVisible(false);
        
        modUsrInternalFrame = new ModificarUsuario(ICU);
        modUsrInternalFrame.setVisible(false);
        
        creEventoInternalFrame = new CrearEvento();
        creEventoInternalFrame.setVisible(false);
        
        creTRegistroInternalFrame = new CrearTipoRegistro(IEV);
        creTRegistroInternalFrame.setVisible(false);
        
        consuTRegistroInternalFrame = new ConsultaTipoRegistro(IEV);
        consuTRegistroInternalFrame.setVisible(false);
        
       
        
        
        frmGestionDeUsuarios.getContentPane().setLayout(null);

        frmGestionDeUsuarios.getContentPane().add(creUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(lisUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(modUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creEventoInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creTRegistroInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(consuTRegistroInternalFrame);
    }

    private void initialize() {
        
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Gestion de Usuarios 1.0");
        frmGestionDeUsuarios.setBounds(100, 100, 450, 400);
        frmGestionDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frmGestionDeUsuarios.setJMenuBar(menuBar);

        JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);

        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicación
                frmGestionDeUsuarios.setVisible(false);
                frmGestionDeUsuarios.dispose();
            }
        });
        menuSistema.add(menuSalir);

        JMenu menuUsuarios = new JMenu("Usuarios");
        menuBar.add(menuUsuarios);

        JMenuItem menuItemRegistrar = new JMenuItem("Alta de Usuario");
        menuItemRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar un usuario
                creUsrInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemRegistrar);

        JMenuItem mntmListaUsuarios = new JMenuItem("Consultar Usuario");
        mntmListaUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver la lista de todos los usuarios,
                // cargando previamente la lista
                lisUsrInternalFrame.cargarUsuarios();
                lisUsrInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(mntmListaUsuarios);
        
        JMenuItem menuModificarUsuario = new JMenuItem("Modificar Datos de Usuario");
        menuModificarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cargar usuarios antes de mostrar la ventana y luego mostrar el InternalFrame
                modUsrInternalFrame.cargarUsuarios();
                modUsrInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuModificarUsuario);
        
        JMenu menuEventos = new JMenu("Eventos y Ediciones");
        menuBar.add(menuEventos);
        
        JMenuItem menuItemAltaEvento = new JMenuItem("Alta de Evento");
        menuItemAltaEvento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de evento
                creEventoInternalFrame.setVisible(true);
            }
        });
        menuEventos.add(menuItemAltaEvento);
        
        JMenuItem menuItemAltaTRegistro = new JMenuItem("Alta Tipo de Registro");
        menuItemAltaTRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de evento
            	creTRegistroInternalFrame.cargarEventos();
            	creTRegistroInternalFrame.setVisible(true);
            	
            }
        });
        menuEventos.add(menuItemAltaTRegistro);
        
        JMenuItem menuItemConsultaTRegistro = new JMenuItem("Consulta Tipo Registro");
        menuItemConsultaTRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de evento
            	consuTRegistroInternalFrame.limpiarFormulario();
            	consuTRegistroInternalFrame.cargarEventos();
            	consuTRegistroInternalFrame.setVisible(true);
            }
        });
        menuEventos.add(menuItemConsultaTRegistro);
    }
}