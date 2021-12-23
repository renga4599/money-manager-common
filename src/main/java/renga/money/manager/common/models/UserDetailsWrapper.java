package renga.money.manager.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsWrapper {
    private List<UserDetails> userDetailsList = new ArrayList<>();
}
