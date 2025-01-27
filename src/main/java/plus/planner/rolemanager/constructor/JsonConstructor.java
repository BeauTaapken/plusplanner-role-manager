package plus.planner.rolemanager.constructor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plus.planner.rolemanager.model.Role;
import plus.planner.rolemanager.model.User;

import javax.persistence.Tuple;
import java.util.List;

@Component
public class JsonConstructor {
    private final ObjectMapper objectMapper;

    @Autowired
    public JsonConstructor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String constructJson(List<Tuple> users) throws JsonProcessingException {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < users.size(); i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            final User user = (User) users.get(i).get("user");
            final Role role = (Role) users.get(i).get("role");
            stringBuilder.append("{\"user\":" + objectMapper.writeValueAsString(user) +
                    ",\"role\":" + objectMapper.writeValueAsString(role) + "}");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
