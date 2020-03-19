package sample;

import Lexico.Lexer;
import Sintactico.Syntax;
import grammar.ascendent.Lexico.*;
import grammar.ascendent.Sintactico.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application implements EventHandler<ActionEvent> {

    Scene scene;
    MenuBar menuBar;
    Menu archivo, analizar, ayuda, reportes;
    MenuItem nuevo, abrir, guardar, cerrar, salir;
    MenuItem errores, simbolos, ast;
    MenuItem compilar_asc, compilar_desc, ejecutar;
    MenuItem acerca;
    HBox hBox;
    TabPane codePane, graphPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Editor de Texto");
        initMenuBar();
        initPane();

        BorderPane layout = new BorderPane();
        hBox = new HBox(10, codePane, graphPane);

        layout.setTop(menuBar);
        layout.setCenter(hBox);

        scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void initMenuBar() {
        menuBar = new MenuBar();

        initMenu();

        menuBar.getMenus().addAll(archivo, analizar, ayuda);
    }

    private void initMenu () {
        archivo = new Menu("Archivo");
        analizar = new Menu("Analizar");
        ayuda = new Menu("Ayuda");

        reportes = new Menu("Reportes");

        initMenuItem();

        archivo.getItems().addAll(nuevo, abrir, guardar, cerrar, salir);
        analizar.getItems().addAll(compilar_asc, compilar_desc, ejecutar, reportes);
        ayuda.getItems().addAll(acerca);
    }

    private void initMenuItem() {
        nuevo = new MenuItem("Nuevo");
        abrir = new MenuItem("Abrir");
        guardar = new MenuItem("Guardar");
        cerrar = new MenuItem("Cerrar");
        salir = new MenuItem("Salir");
        compilar_asc = new MenuItem("Compilar asc");
        compilar_desc = new MenuItem("Compilar desc");
        ejecutar = new MenuItem("Ejecutar");
        acerca = new MenuItem("Acerca");

        errores = new MenuItem("Errores");
        simbolos = new MenuItem("Tabla de Simbolos");
        ast = new MenuItem("AST");

        nuevo.setOnAction(this);
        abrir.setOnAction(this);
        guardar.setOnAction(this);
        cerrar.setOnAction(this);
        salir.setOnAction(this);
        compilar_asc.setOnAction(this);
        compilar_desc.setOnAction(this);
        ejecutar.setOnAction(this);
        acerca.setOnAction(this);

        errores.setOnAction(this);
        simbolos.setOnAction(this);
        ast.setOnAction(this);

        reportes.getItems().addAll(errores, simbolos, ast);
    }

    private void initPane() {
        codePane = new TabPane();
        graphPane = new TabPane();

        codePane.autosize();
        codePane.setMinWidth(400);
        codePane.setMaxWidth(700);

        graphPane.autosize();
        graphPane.setMinWidth(400);
        graphPane.setMaxWidth(700);
    }

    @Override
    public void handle(ActionEvent event) {
        int currentIndex = codePane.getSelectionModel().getSelectedIndex();
        if (event.getSource() == nuevo) {
            newTab(codePane, "Unknown", "", "");
        }
        else if (event.getSource() == abrir) {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(null);
            if (file != null) {
                String content = readFile(file);
                newTab(codePane, file.getName(), file.getAbsolutePath(), content);
            }
        }
        else if (event.getSource() == guardar) {
            saveFile(currentIndex);
        }
        else if (event.getSource() == cerrar) {
            codePane.getTabs().remove(currentIndex);
        }
        else if (event.getSource() == salir) {

        }
        else if (event.getSource() == compilar_asc) {
            cup_compile(currentIndex);
        }
        else if (event.getSource() == compilar_desc) {

        }
        else if (event.getSource() == ejecutar) {

        }
        else if (event.getSource() == acerca) {

        }
        else if (event.getSource() == errores) {

        }
        else if (event.getSource() == simbolos) {

        }
        else if (event.getSource() == ast) {

        }
    }

    private void cup_compile(int currentIndex) {
        Tab tab = codePane.getTabs().get(currentIndex);
        String filename = tab.getTooltip().getText();
        try {
            Lexer lexer = new Lexer(
                    new BufferedReader(
                            new FileReader(filename)
                    )
            );
            Syntax syntax = new Syntax(lexer);
            syntax.parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("¡Compilación finalizada!");
        }
    }

    private void saveFile(int currentIndex) {
        Tab tab = codePane.getTabs().get(currentIndex);
        TextArea area = (TextArea) tab.getContent();
        String filename = tab.getTooltip().getText();

        if (filename.isEmpty()) {
            FileChooser chooser = new FileChooser();
            File file = chooser.showSaveDialog(null);
            if (file == null) return;
            filename = file.getAbsolutePath();
            codePane.getTabs().get(currentIndex).getTooltip().setText(filename);
            codePane.getTabs().get(currentIndex).setText(file.getName());
        }

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(area.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(File file) {
        String content = "";

        try (FileReader reader = new FileReader(file)) {
            int value = reader.read();
            while (value != -1) {
                content += (char)value;
                value = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return content;
        }
    }

    private void newTab(TabPane tabPane, String title, String path, String content) {
        TextArea area = new TextArea();
        area.setText(content);
        area.setFont(Font.font("Consolas"));
        area.setWrapText(true);
        area.requestFocus();
/*
        CodeArea area = new CodeArea();
        area.setWrapText(true);
        area.setParagraphGraphicFactory(LineNumberFactory.get(area));
        area.replaceText(content);
*/

        Tab tab = new Tab(title, area);
        tab.setTooltip(new Tooltip(path));
        tabPane.getTabs().add(tab);
    }

}
