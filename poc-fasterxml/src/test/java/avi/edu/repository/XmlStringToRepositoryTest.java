package avi.edu.repository;

import com.fasterxml.jackson.xml.XmlMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static org.assertj.core.api.Assertions.assertThat;

public class XmlStringToRepositoryTest {
    private XmlMapper mapper = new XmlMapper();

    @Test
    public void should_deserialize_xml_string_to_a_repository() throws IOException {
        String xmlInput = new String("<repository><id>ID</id><name>some repo</name></repository>");
        Repository repository = mapper.readValue(xmlInput, Repository.class);
        assertThat(repository.getId()).isEqualTo("ID");
        assertThat(repository.getName()).isEqualTo("some repo");
    }

    @Test
    public void should_deserialize_xml_string_to_repositories() throws IOException {
        String xmlInput = setUpRepositoriesXmlString();
        System.out.println(xmlInput);
        List<Repository> repositories = mapper.readValue(xmlInput, List.class);
        assertThat(repositories.size()).isEqualTo(2);
        assertThat(repositories).isEqualTo(of(new Repository("ID1", "repo1"), new Repository("ID2", "repo2")));
    }

    private String setUpRepositoriesXmlString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("<repositories>");
        buffer.append("<repository>");
        buffer.append("<id>ID1</id>");
        buffer.append("<name>repo1</name>");
        buffer.append("</repository>");
        buffer.append("<repository>");
        buffer.append("<id>ID2</id>");
        buffer.append("<name>repo2</name>");
        buffer.append("</repository>");
        buffer.append("</repositories>");

        return buffer.toString();
    }
}
