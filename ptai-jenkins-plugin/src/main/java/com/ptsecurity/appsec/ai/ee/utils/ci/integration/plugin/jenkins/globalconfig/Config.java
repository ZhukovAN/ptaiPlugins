package com.ptsecurity.appsec.ai.ee.utils.ci.integration.plugin.jenkins.globalconfig;

import com.ptsecurity.appsec.ai.ee.utils.ci.integration.plugin.jenkins.Messages;
import com.ptsecurity.appsec.ai.ee.utils.ci.integration.plugin.jenkins.serversettings.ServerSettings;
import com.ptsecurity.appsec.ai.ee.utils.ci.integration.plugin.jenkins.utils.Validator;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import jenkins.model.Jenkins;
import lombok.Getter;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import java.io.Serializable;

public class Config extends AbstractDescribableImpl<Config> implements Cloneable, Serializable {
    @Getter
    protected String configName;

    @Getter
    private ServerSettings serverSettings;

    @DataBoundConstructor
    public Config(
            final String configName,
            final ServerSettings serverSettings) {
        this.configName = configName;
        this.serverSettings = serverSettings;
    }

    @Override
    public Config clone() {
        try {
            return (Config) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public ConfigDescriptor getDescriptor() {
        return Jenkins.get().getDescriptorByType(ConfigDescriptor.class);
    }

    @Extension
    public static class ConfigDescriptor extends Descriptor<Config> {
        @Override public String getDisplayName() {
            return Messages.captions_config_displayName();
        }

        public FormValidation doCheckConfigName(@QueryParameter("configName") String configName) {
            return Validator.doCheckFieldNotEmpty(configName, Messages.validator_check_field_empty());
        }
    }
}
