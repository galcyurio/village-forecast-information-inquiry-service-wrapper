package core

import com.github.galcyurio.config.Vfiis
import com.github.galcyurio.core.RawVfiisBinder
import com.github.galcyurio.core.RawVfiisService
import com.github.galcyurio.response.ForecastGribResponse
import com.github.galcyurio.response.ForecastSpaceDataResponse
import com.github.galcyurio.response.ForecastTimeDataResponse
import com.github.galcyurio.response.ForecastVersionCheckResponse
import mock.MockRawVfiisService
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

import static org.assertj.core.api.Java6Assertions.assertThat
/**
 * @author galcyurio
 */
class RawVfiisBinderMockUnitTest {

    private static MockRawVfiisService sMockRawVfiisService

    @BeforeClass
    static void beforeClass() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Vfiis.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()

        BehaviorDelegate<RawVfiisService> delegate = new MockRetrofit.Builder(retrofit)
                .build()
                .create(RawVfiisService.class)

        sMockRawVfiisService = new MockRawVfiisService(delegate)
    }

    @Test
    void fetchForecastGribTest() throws Exception {
        Map<String, Object> rawResponse = sMockRawVfiisService.fetchForecastGrib(
                "",
                "", "",
                0, 0,
                0, 0)
                .execute().body()
        ForecastGribResponse response = RawVfiisBinder.fromRawGribResponse(rawResponse)
        assertThat(response).hasNoNullFieldsOrProperties()
    }

    @Test
    void fetchForecastSpaceData() throws Exception {
        Map<String, Object> rawResponse = sMockRawVfiisService.fetchForecastSpaceData(
                "",
                "", "",
                0, 0,
                0, 0)
                .execute().body()
        ForecastSpaceDataResponse response = RawVfiisBinder.fromRawSpaceDataResponse(rawResponse)
        assertThat(response).hasNoNullFieldsOrProperties()
    }

    @Test
    void fetchForecastTimeData() throws Exception {
        Map<String, Object> rawResponse = sMockRawVfiisService.fetchForecastTimeData(
                "",
                "", "",
                0, 0,
                0, 0)
                .execute().body()
        ForecastTimeDataResponse response = RawVfiisBinder.fromRawTimeDataResponse(rawResponse)
        assertThat(response).hasNoNullFieldsOrProperties()
    }

    @Test
    void fetchForecastVersionCheck() throws Exception {
        Map<String, Object> rawResponse = sMockRawVfiisService.fetchForecastVersionCheck(
                "", "", "")
                .execute().body()
        ForecastVersionCheckResponse response = RawVfiisBinder.fromRawVersionCheckResponse(rawResponse)
        assertThat(response).hasNoNullFieldsOrProperties()
    }

}
