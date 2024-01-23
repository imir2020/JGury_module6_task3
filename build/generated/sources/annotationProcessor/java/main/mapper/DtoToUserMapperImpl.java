package mapper;

import dto.CreateUserDto;
import entity.Status;
import entity.User;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-23T20:57:08+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DtoToUserMapperImpl implements DtoToUserMapper {

    @Override
    public User dtoToUser(CreateUserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( userDto.getName() );
        if ( userDto.getBirthday() != null ) {
            user.birthday( LocalDate.parse( userDto.getBirthday() ) );
        }
        user.password( userDto.getPassword() );
        if ( userDto.getStatus() != null ) {
            user.status( Enum.valueOf( Status.class, userDto.getStatus() ) );
        }

        return user.build();
    }
}
