package com.evil.mvp;

import com.evil.mvp.template.Template;
import com.evil.mvp.template.TemplateManager;

public class BaseJavaMvpHelper extends BaseHelper {
    @Override
    protected Template[] getTemplateList(TemplateManager manager) {
        return new Template[]{manager.getTemplateMvpJava(), manager.getTemplatePresenterJava(), manager.getTemplateModelJava()};
    }
}
