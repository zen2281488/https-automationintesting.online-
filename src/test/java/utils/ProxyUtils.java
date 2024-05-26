package utils;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;

public class ProxyUtils {

    private BrowserMobProxy proxy;

    public BrowserMobProxy startProxy(){
        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        return proxy;
    }
    public Proxy getSeleniumProxy() {
        return ClientUtil.createSeleniumProxy(proxy);
    }

    public ChromeOptions getChromeOptionsWithProxy() {
        Proxy seleniumProxy = getSeleniumProxy();
        ChromeOptions options = new ChromeOptions();
        options.setProxy(seleniumProxy);
        return options;
    }

    public void stopProxy() {
        if (proxy != null) {
            proxy.stop();
        }
    }
}