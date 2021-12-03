package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.service.ReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/report")
class ReportController(
    @Autowired val reportService: ReportService
) {

    @GetMapping("/chauffeurs")
    fun generateChauffeursReport() {
        reportService.generateChauffeur()
    }

    @GetMapping("/incidents")
    fun generateIncidentReport() {
        reportService.generateIncidentsWithNadCoverage()
    }
}