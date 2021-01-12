package com.evil.mvp;

import com.evil.mvp.template.Template;
import com.evil.mvp.template.TemplateManager;

public class BaseKtMvpHelper extends BaseHelper {
    @Override
    protected Template[] getTemplateList(TemplateManager manager) {
        return new Template[]{manager.getTemplateMvpKt(), manager.getTemplatePresenterKt(), manager.getTemplateModelKt()};
    }
}
