package com.evil.mvp;

import com.evil.mvp.dialog.InputDialog;
import com.evil.mvp.template.Template;
import com.evil.mvp.template.TemplateManager;
import com.evil.mvp.util.FileUtils;
import com.evil.mvp.util.PackageManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.apache.http.util.TextUtils;

import java.awt.*;

public abstract class BaseHelper extends AnAction {
    private Project project;
    private String idePath;
    private String pathPackageName;

    @Override
    public void actionPerformed(AnActionEvent event) {
        project = event.getData(PlatformDataKeys.PROJECT);
        idePath = FileUtils.getCursorPath(event);
        idePath = FileUtils.checkPath(idePath);
        pathPackageName = PackageManager.getPathPackageName(idePath);
        if (TextUtils.isEmpty(pathPackageName)) {
            Messages.showMessageDialog(project, "Directory is error!", "Error", Messages.getErrorIcon());
            return;
        }
        showDialog();
    }

    private void showDialog() {
        InputDialog dialog = new InputDialog();
        dialog.setTitle("Please input mvp model name!");
        dialog.setMinimumSize(new Dimension(300, 50));
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setBounds(size.width / 2 - 150, size.height / 2 - 25, 300, 50);
        dialog.setResultCallback(modelName -> {
            createCode(modelName);
        });
        dialog.pack();
        dialog.setVisible(true);
    }

    private void createCode(String modelName) {
        if (!TextUtils.isEmpty(modelName)) {
            TemplateManager manager = new TemplateManager(this, idePath, modelName);
            Template[] templateList = getTemplateList(manager);
            for (Template template : templateList) {
                createTemplateFile(template,modelName);
            }
        }else {
            Messages.showMessageDialog(project, "Failed to create MVP!No name entered!", "Error", Messages.getErrorIcon());
        }
    }

    private void createTemplateFile(Template template,String moduleName) {
        String fillContent = FileUtils.fillTemplateContent(template.getContent(), moduleName, pathPackageName+"."+TemplateManager.toLower(moduleName));
        FileUtils.writeToFile(fillContent, template.getDir(), template.getFileName());
    }

    protected abstract Template[] getTemplateList(TemplateManager manager);
}
