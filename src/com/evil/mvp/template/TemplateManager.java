package com.evil.mvp.template;

import com.evil.mvp.util.FileUtils;
import com.intellij.openapi.actionSystem.AnAction;

import java.io.InputStream;

public class TemplateManager {

    private AnAction action;
    private String idePath;
    private String packagePath;
    private String moduleName;

    public TemplateManager(AnAction action, String idePath, String moduleName) {
        this.action = action;
        this.idePath = idePath;
        this.moduleName = moduleName;
        this.packagePath = idePath+"/"+toLower(moduleName);
    }

    public static String toLower(String content){
        StringBuffer buffer = new StringBuffer();
        char[] array = content.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            if (c >='A'&& c <='Z') {
                if (i >0){
                    buffer.append("_");
                    buffer.append(Character.toLowerCase(c));
                }else {
                    buffer.append(Character.toLowerCase(c));
                }
            }else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    /**
     * Kotlin Contract 模版
     */
    public Template getTemplateMvpKt() {
        InputStream tContractInput = action.getClass().getResourceAsStream("/com/evil/mvp/template/kt/TMvpKt.txt");
        String tContractContent = FileUtils.readTemplateFile(tContractInput);
        String contractFileName = moduleName + "Mvp.kt";
        return new Template(packagePath, contractFileName, tContractContent);
    }

    /**
     * Kotlin Presenter 模版
     */
    public Template getTemplatePresenterKt() {
        InputStream tPresenterInput = this.getClass().getResourceAsStream("/com/evil/mvp/template/kt/TPresenterKt.txt");
        String tPresenterContent = FileUtils.readTemplateFile(tPresenterInput);
        String presenterFileName = moduleName + "Presenter.kt";
        return new Template(packagePath, presenterFileName, tPresenterContent);
    }

    /**
     * Kotlin Model 模版
     */
    public Template getTemplateModelKt() {
        InputStream tModelInput = this.getClass().getResourceAsStream("/com/evil/mvp/template/kt/TModelKt.txt");
        String tModelContent = FileUtils.readTemplateFile(tModelInput);
        String modelFileName = moduleName + "Model.kt";
        return new Template(packagePath, modelFileName, tModelContent);
    }


    /**
     * Java Contract 模版
     */
    public Template getTemplateMvpJava() {
        //创建Contract
        InputStream tContractInput = this.getClass().getResourceAsStream("/com/evil/mvp/template/java/TMvpJava.txt");
        String tContractContent = FileUtils.readTemplateFile(tContractInput);
        String contractFileName = moduleName + "Mvp.java";
        return new Template(packagePath, contractFileName, tContractContent);
    }

    /**
     * Java Presenter 模版
     */
    public Template getTemplatePresenterJava() {
        InputStream tPresenterInput = this.getClass().getResourceAsStream("/com/evil/mvp/template/java/TPresenterJava.txt");
        String tPresenterContent = FileUtils.readTemplateFile(tPresenterInput);
        String presenterFileName = moduleName + "Presenter.java";
        return new Template(packagePath, presenterFileName, tPresenterContent);
    }

    /**
     * Java Model 模版
     */
    public Template getTemplateModelJava() {
        InputStream tModelInput = this.getClass().getResourceAsStream("/com/evil/mvp/template/java/TModelJava.txt");
        String tModelContent = FileUtils.readTemplateFile(tModelInput);
        String modelFileName = moduleName + "Model.java";
        return new Template(packagePath, modelFileName, tModelContent);
    }

}
