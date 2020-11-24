package modpackcurseforgeparse.classes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Title",
        "Packurl",
        "Author",
        "AuthorHome",
        "Downloads",
        "Updated",
        "Created",
        "Description",
        "tags",
        "gameVersions"
})
public class ModPackClass {


    @JsonProperty("Title")
    private String title;
    @JsonProperty("Packurl")
    private String packurl;
    @JsonProperty("Author")
    private String author;
    @JsonProperty("AuthorHome")
    private String authorHome;
    @JsonProperty("Downloads")
    private String downloads;
    @JsonProperty("Updated")
    private String updated;
    @JsonProperty("Created")
    private String created;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("gameVersions")
    private List<String> gameVersions = null;

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("Packurl")
    public String getPackurl() {
        return packurl;
    }

    @JsonProperty("Packurl")
    public void setPackurl(String packurl) {
        this.packurl = packurl;
    }

    @JsonProperty("Author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("Author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("AuthorHome")
    public String getAuthorHome() {
        return authorHome;
    }

    @JsonProperty("AuthorHome")
    public void setAuthorHome(String authorHome) {
        this.authorHome = authorHome;
    }

    @JsonProperty("Downloads")
    public String getDownloads() {
        return downloads;
    }

    @JsonProperty("Downloads")
    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    @JsonProperty("Updated")
    public String getUpdated() {
        return updated;
    }

    @JsonProperty("Updated")
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @JsonProperty("Created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("Created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("gameVersions")
    public List<String> getGameVersions(){return gameVersions; }

    @JsonProperty("gameVersions")
    public void setGameVersions(List<String> gameVersions){this.gameVersions = gameVersions;}

}