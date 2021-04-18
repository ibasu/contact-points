package common

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ConsoleNotifier

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

class MockServer {

    private static WireMockServer wireMockServer

    static startServer(){
        if (wireMockServer == null){
            wireMockServer = new WireMockServer(options()
                    .port(8095)
                    .bindAddress("localhost")
                    .usingFilesUnderClasspath("stubdata/mocks")
                    .notifier(new ConsoleNotifier(true))

            )
        }

        if (!wireMockServer.isRunning()){
            wireMockServer.start()
        }
    }
}
