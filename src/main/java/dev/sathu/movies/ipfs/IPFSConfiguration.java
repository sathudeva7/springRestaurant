package dev.sathu.movies.ipfs;

import io.ipfs.api.IPFS;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class IPFSConfiguration {

    IPFS ipfs;
    public IPFSConfiguration() throws IOException {
        try {
            IPFS ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
            ipfs.refs.local();
        } catch (Exception e) {
            System.out.println("psa"+e);
        }
    }
}
