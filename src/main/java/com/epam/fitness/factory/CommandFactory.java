package com.epam.fitness.factory;

import com.epam.fitness.command.*;
import com.epam.fitness.command.program.*;
import com.epam.fitness.command.user.*;
import com.epam.fitness.service.ExerciseService;
import com.epam.fitness.service.ProgramService;
import com.epam.fitness.service.UserService;

public class CommandFactory {
    private final ServiceFactory serviceFactory;

    public CommandFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public Command create(String command) {
        Command commandResult;
        if(command == null) {
            return new InitialCommand();
        }
        switch (command) {
            case "login": {
                UserService userService = serviceFactory.getUserService();
                commandResult = new LoginCommand(userService);
                break;
            }
            case "logout": {
                commandResult = new LogoutCommand();
                break;
            }
            case "show_all_programs_with_exercises": {
                ProgramService programService = serviceFactory.getProgramService();
                commandResult = new ShowProgramsWithExercisesCommand(programService);
                break;
            }
            case "edit_program_form": {
                ProgramService programService = serviceFactory.getProgramService();
                commandResult = new ShowEditProgramFormCommand(programService);
                break;
            }
            case "show_payments": {
                ProgramService programService = serviceFactory.getProgramService();
                commandResult = new ShowProgramsCommand(programService);
                break;
            }
            case "new_program_form": {
                UserService userService = serviceFactory.getUserService();
                commandResult = new ShowNewProgramFormCommand(userService);
                break;
            }
            case "update_program": {
                ProgramService programService = serviceFactory.getProgramService();
                ExerciseService exerciseService = serviceFactory.getExerciseService();
                commandResult = new UpdateProgramCommand(programService, exerciseService);
                break;
            }
            case "create_program": {
                ProgramService programService = serviceFactory.getProgramService();
                ExerciseService exerciseService = serviceFactory.getExerciseService();
                commandResult = new CreateNewProgramCommand(programService, exerciseService);
                break;
            }
            case "delete_program": {
                ProgramService programService = serviceFactory.getProgramService();
                ExerciseService exerciseService = serviceFactory.getExerciseService();
                commandResult = new DeleteProgramCommand(programService, exerciseService);
                break;
            }
            case "show_users": {
                UserService userService = serviceFactory.getUserService();
                commandResult = new ShowUsersCommand(userService);
                break;
            }
            case "delete_user": {
                UserService userService = serviceFactory.getUserService();
                commandResult = new DeleteUserCommand(userService);
                break;
            }
            case "edit_user_form": {
                UserService userService = serviceFactory.getUserService();
                commandResult = new ShowEditUserFormCommand(userService);
                break;
            }
            case "new_user_form": {
                commandResult = new ShowNewUserFormCommand();
                break;
            }
            case "update_user": {
                UserService userService = serviceFactory.getUserService();
                commandResult = new UpdateUserCommand(userService);
                break;
            }
            case "create_user": {
                UserService userService = serviceFactory.getUserService();
                commandResult = new CreateNewUserCommand(userService);
                break;
            }
            case "change_language": {
                commandResult = new ChangeLanguageCommand();
                break;
            }
            default: {
                commandResult = new InitialCommand();
            }

        }
        System.out.println(commandResult);
        return commandResult;
    }
}
