package rws;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Paths;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import static common.Comm.getAllFolderContent;
import static common.Comm.findFiles;

/**
 * REST Web Service
 *
 * @author il.lenskii
 */
@Path("dir")
public class GenericResource implements IDirectory {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    @Override
    @GET
    @Path("{directory}") 
    @Produces("text/html")
    public String getContent(@PathParam("directory") String dir) {
        
        String decDir = URLDecoder.decode(dir);
        
        ArrayList<String> res = getAllFolderContent( Paths.get(decDir) );
        
        if(res.size() < 1) {
            
            return null;
        }

        return res.toString();
    }

    @Override
    @GET
    @Path("find/{directory}") 
    @Produces("text/html")
    public String findFile(
            @PathParam("directory") String dir
            ,@QueryParam("file") String file) {
        
        String decDir = URLDecoder.decode(dir);
        
        ArrayList<String> res = findFiles(Paths.get(decDir), file);
        
        if(res.size() < 1) {
            
            return null;
        }

        return res.toString();
    }
}
