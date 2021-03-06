package com.ptsecurity.appsec.ai.ee.utils.ci.integration.plugin.jenkins.actions.AstJobSingleResult

import lib.FormTagLib
import lib.LayoutTagLib

def f = namespace(FormTagLib)
def l = namespace(LayoutTagLib)
def st = namespace("jelly:stapler")

l.layout(title: "PT AI AST report") {
    l.side_panel() {
        st.include(page: "sidepanel.jelly", it: my.run, optional: true)
    }
    l.main_panel() {
        h1("List of PT AI issues for this build")
    }
}
