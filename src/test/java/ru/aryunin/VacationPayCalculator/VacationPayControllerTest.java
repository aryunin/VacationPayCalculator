package ru.aryunin.VacationPayCalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.aryunin.VacationPayCalculator.Controllers.VacationPayController;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationPayController.class)
@Import(TestConfig.class)
public class VacationPayControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculate_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .param("startDate", "2022-11-03")
                .param("endDate", "2022-11-15")
                .param("averageSalary", "65000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(17747.44)));
    }
}
