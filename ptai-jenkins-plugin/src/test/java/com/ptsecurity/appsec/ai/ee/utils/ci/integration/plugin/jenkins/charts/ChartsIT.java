package com.ptsecurity.appsec.ai.ee.utils.ci.integration.plugin.jenkins.charts;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptsecurity.appsec.ai.ee.ptai.server.v36.projectmanagement.model.*;
import com.ptsecurity.appsec.ai.ee.utils.ci.integration.BaseIT;
import com.ptsecurity.appsec.ai.ee.utils.ci.integration.base.Base;
import com.ptsecurity.appsec.ai.ee.utils.ci.integration.ptaiserver.utils.IssuesModelHelper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.ptsecurity.appsec.ai.ee.utils.ci.integration.base.Base.callApi;

@DisplayName("AST results charts generation integration tests")
@Tag("integration")
class ChartsIT extends BaseIT {
    @DisplayName("Generate vulnerability level distribution chart for randomly chosen scan result")
    @Test
    @SneakyThrows
    public void test() {
        // Prepare date -> issues map
        List<Triple<Integer, LocalDateTime, IssuesModel>> issuesModelList = new ArrayList<>();
        // Get random scan result
        ScanResult randomScanResult = getRandomScanResult();
        Assertions.assertNotNull(randomScanResult, "Randomly chosen scan result is null");
        // Get all scan results for random project
        List<ScanResult> projectScanResults = projectsApi.apiProjectsProjectIdScanResultsGet(randomScanResult.getProjectId(), AuthScopeType.ACCESSTOKEN);
        projectScanResults.sort(Comparator.comparing(ScanResult::getScanDate));
        int counter = 0;
        for (ScanResult scanResult : projectScanResults) {
            File json = projectsApi.apiProjectsProjectIdScanResultsScanResultIdIssuesGet(scanResult.getProjectId(), scanResult.getId(), null);
            IssuesModel issues = IssuesModelHelper.parse(new FileInputStream(json));
            Base.callApi(
                    () -> json.delete(),
                    "Temporal file " + json.getPath() + " delete failed", true);

            LocalDateTime dateTime = LocalDateTime.parse(scanResult.getScanDate(), DateTimeFormatter.ISO_DATE_TIME);
            issuesModelList.add(new ImmutableTriple<>(counter++, dateTime, issues));
        }

        StackedAreaChartDataModel model = StackedAreaChartDataModel.create(issuesModelList);
        String modelJson = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(model);
        System.out.println(modelJson);
   }

    @DisplayName("Load OWASP bricks scan result")
    @Test
    @SneakyThrows
    public void downloadVersatileResults() {
        Optional<Project> bricksProject = projectsApi.apiProjectsGet(true).stream()
                .filter(p -> p.getName().equalsIgnoreCase("Bricks"))
                .findAny();
        Assertions.assertTrue(bricksProject.isPresent(), "OWASP Bricks project not found");
        List<ScanResult> projectScanResults = projectsApi.apiProjectsProjectIdScanResultsGet(bricksProject.get().getId(), AuthScopeType.ACCESSTOKEN);
        Assertions.assertFalse(
                null == projectScanResults || projectScanResults.isEmpty() ,
                "OWASP Bricks scan results not found");
        // Sort scan results
        projectScanResults.sort((r1, r2) -> - r1.getScanDate().compareTo(r2.getScanDate()));
        // Get latest scan result
        ScanResult scanResult = projectScanResults.get(0);
        Assertions.assertNotNull(scanResult, "Latest scan result is null");
        File json = projectsApi.apiProjectsProjectIdScanResultsScanResultIdIssuesGet(scanResult.getProjectId(), scanResult.getId(), null);
        IssuesModel issues = IssuesModelHelper.parse(new FileInputStream(json));
        callApi(
                () -> json.delete(),
                "Temporal file " + json.getPath() + " delete failed", true);
    }
}