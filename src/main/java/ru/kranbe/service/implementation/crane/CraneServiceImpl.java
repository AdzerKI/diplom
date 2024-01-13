package ru.kranbe.service.implementation.crane;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kranbe.domain.crane.Crane;
import ru.kranbe.domain.exception.ResourceNotFoundException;
import ru.kranbe.repository.CraneRepository;
import ru.kranbe.service.CraneService;
import ru.kranbe.web.dto.crane.open.RequestPropertyListByType;
import ru.kranbe.web.dto.crane.open.ResponseGetCraneType;
import ru.kranbe.web.dto.crane.open.ResponsePropertyListByType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class CraneServiceImpl implements CraneService {
    private final CraneRepository craneRepository;

    @Override
    public Crane create(Crane crane, Long userId) {
        Crane craneChecked = checkCraneFields(crane);

        return craneRepository.save(craneChecked);
    }

//    @Override
//    public CraneEntity read(CraneEntity crane) {
//        CraneEntity craneChecked = checkCraneFields(crane);
//
//        return craneRepository.save(craneChecked);
//    }
    @Override
    public Crane update(Crane crane) {
        Crane craneChecked = checkCraneFields(crane);

        return craneRepository.save(craneChecked);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Crane getById(Long id) {
        return null;
    }

    @Override
    public ResponsePropertyListByType getPropertiesByType(RequestPropertyListByType request) {
        ResponsePropertyListByType response = new ResponsePropertyListByType();

        Set<String> fields = new HashSet<>();

        // Общие для всех
        fields.add("design");	//	Дизайн крана
        fields.add("load_capacity");	//	Грузоподъемность крана
        fields.add("lifting_height");	//	Высота подъема
        fields.add("cart_lifting_mechanism");	//	Механизм подъема
        fields.add("operating_regime_group");	//	Группа режима работы
        fields.add("placement_category");	//	Категория размещения
        fields.add("temperature_regime_from");	//	Температурный режим от
        fields.add("temperature_regime_to");	//	Температурный режим до
        fields.add("quantity");	//	дополнительные требования, количество
        fields.add("is_delivery");	//	дополнительные требования, доставка
        fields.add("installation");	//	дополнительные требования, монтаж пусконаладка
        fields.add("file");	//	прикрепить файл
        fields.add("other_requirement");	//	прочие требования

        switch (request.getType()){
            case "OVERHEAD_ELECTRIC_SINGLE_GIRDER_SUPPORT_CRANE": //Кран мостовой электрический однобалочный опорный
            case "OVERHEAD_ELECTRIC_SINGLE_GIRDER_SUPPORT_BOX_CRANE": //Кран мостовой электрический однобалочный опорный коробчатый
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("is_radio_control");	//	Тип управления, радиоуправление
                fields.add("is_remote_control");	//	Тип управления, подвесной пульт
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("crane_speed");	//	Скорость передвиж. крана / МПУ, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_crane_movement");	//	Концевые переключатели передвиж. крана
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("beam");	//	Балка
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            //Кран мостовой электрический двухбалочный опорный
            case "OVERHEAD_ELECTRIC_DOUBLE_GIRDER_SUPPORT_CRANE":
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("is_service_platform");	//	Площадка обслуживания
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("is_radio_control");	//	Тип управления, радиоуправление
                fields.add("is_remote_control");	//	Тип управления, подвесной пульт
                fields.add("is_cabin_control");	//	Тип управления, из кабины
                fields.add("bridge_gallery");	//	галерея вдоль моста
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("crane_speed");	//	Скорость передвиж. крана / МПУ, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_crane_movement");	//	Концевые переключатели передвиж. крана
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("beam");	//	Балка
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            // Кран полукозловой электрический однобалочный
            case "SEMI_CRANE_ELECTRIC_SINGLE_GIRDER":
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("is_service_platform");	//	Площадка обслуживания
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("is_radio_control");	//	Тип управления, радиоуправление
                fields.add("is_remote_control");	//	Тип управления, подвесной пульт
                fields.add("is_cabin_control");	//	Тип управления, из кабины
                fields.add("movement_surface");	//	Передвижение МПУ (поверхность)
                fields.add("is_without_console");	//	без консолей
                fields.add("right_console");	//	консоль правая
                fields.add("left_console");	//	консоль левая
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("crane_speed");	//	Скорость передвиж. крана / МПУ, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_crane_movement");	//	Концевые переключатели передвиж. крана
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("beam");	//	Балка
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_value_four");	//	Схема, значение 4
                fields.add("scheme_value_five");	//	Схема, значение 5
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            // Кран мостовой электрический однобалочный подвесной коробчатый
            case "OVERHEAD_ELECTRIC_SINGLE_GIRDER_SUSPENDED_BOX_CRANE":
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("is_radio_control");	//	Тип управления, радиоуправление
                fields.add("is_remote_control");	//	Тип управления, подвесной пульт
                fields.add("is_cabin_control");	//	Тип управления, из кабины
                fields.add("is_without_console");	//	без консолей
                fields.add("right_console");	//	консоль правая
                fields.add("left_console");	//	консоль левая
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("crane_speed");	//	Скорость передвиж. крана / МПУ, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_crane_movement");	//	Концевые переключатели передвиж. крана
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("beam");	//	Балка
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_value_four");	//	Схема, значение 4
                fields.add("scheme_value_five");	//	Схема, значение 5
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            // Кран мостовой электрический однобалочный подвесной двухпролетный
            case "BRIDGE_CRANE_ELECTRIC_SINGLE_GIRDER_SUSPENDED_DOUBLE_SPAN":
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("cantilever_span_two");	//	Пролёт, дополнительный
                fields.add("is_service_platform");	//	Площадка обслуживания
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("is_radio_control");	//	Тип управления, радиоуправление
                fields.add("is_remote_control");	//	Тип управления, подвесной пульт
                fields.add("is_without_console");	//	без консолей
                fields.add("right_console");	//	консоль правая
                fields.add("left_console");	//	консоль левая
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("crane_speed");	//	Скорость передвиж. крана / МПУ, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_crane_movement");	//	Концевые переключатели передвиж. крана
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("beam");	//	Балка
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_value_four");	//	Схема, значение 4
                fields.add("scheme_value_five");	//	Схема, значение 5
                fields.add("scheme_value_six");	//	Схема, значение 6
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            // Кран мостовой электрический двухбалочный подвесной
            case "OVERHEAD_ELECTRIC_DOUBLE_GIRDER_OVERHEAD_CRANE":
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("is_service_platform");	//	Площадка обслуживания
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("is_radio_control");	//	Тип управления, радиоуправление
                fields.add("is_remote_control");	//	Тип управления, подвесной пульт
                fields.add("is_cabin_control");	//	Тип управления, из кабины
                fields.add("is_without_console");	//	без консолей
                fields.add("right_console");	//	консоль правая
                fields.add("left_console");	//	консоль левая
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("crane_speed");	//	Скорость передвиж. крана / МПУ, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_crane_movement");	//	Концевые переключатели передвиж. крана
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("beam");	//	Балка
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_value_four");	//	Схема, значение 4
                fields.add("scheme_value_five");	//	Схема, значение 5
                fields.add("scheme_value_six");	//	Схема, значение 6
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            // Кран мостовой электрический двухбалочный подвесной двухпролетный
            case "OVERHEAD_ELECTRIC_DOUBLE_GIRDER_SUSPENDED_DOUBLE_SPAN_CRANE":
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("cantilever_span_two");	//	Пролёт, дополнительный
                fields.add("is_service_platform");	//	Площадка обслуживания
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("is_radio_control");	//	Тип управления, радиоуправление
                fields.add("is_remote_control");	//	Тип управления, подвесной пульт
                fields.add("is_cabin_control");	//	Тип управления, из кабины
                fields.add("is_without_console");	//	без консолей
                fields.add("right_console");	//	консоль правая
                fields.add("left_console");	//	консоль левая
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("crane_speed");	//	Скорость передвиж. крана / МПУ, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_crane_movement");	//	Концевые переключатели передвиж. крана
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("beam");	//	Балка
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_value_four");	//	Схема, значение 4
                fields.add("scheme_value_five");	//	Схема, значение 5
                fields.add("scheme_value_six");	//	Схема, значение 6
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            // Кран мостовой подвесной ручной
            case "BRIDGE_CRANE_SUSPENDED_MANUAL":
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("is_without_console");	//	без консолей
                fields.add("right_console");	//	консоль правая
                fields.add("left_console");	//	консоль левая
                fields.add("beam");	//	Балка
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_value_four");	//	Схема, значение 4
                fields.add("scheme_value_five");	//	Схема, значение 5
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            case "ELECTRIC_SINGLE_GIRDER_GANTRY_CRANE": //Кран козловой электрический однобалочный
            case "GANTRY_CRANE_ELECTRIC_TRUSS": //Кран козловой электрический ферменный
            case "ELECTRIC_DOUBLE_GIRDER_GANTRY_CRANE": //Кран козловой электрический двухбалочный
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("is_service_platform");	//	Площадка обслуживания
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("is_radio_control");	//	Тип управления, радиоуправление
                fields.add("is_remote_control");	//	Тип управления, подвесной пульт
                fields.add("is_cabin_control");	//	Тип управления, из кабины
                fields.add("is_without_console");	//	без консолей
                fields.add("right_console");	//	консоль правая
                fields.add("left_console");	//	консоль левая
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("crane_speed");	//	Скорость передвиж. крана / МПУ, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_crane_movement");	//	Концевые переключатели передвиж. крана
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("beam");	//	Балка
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_value_four");	//	Схема, значение 4
                fields.add("scheme_value_five");	//	Схема, значение 5
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            //Мобильное перегрузочное устройство (МПУ)
            case "MOBILE_RELOADING_DEVICE_MPU":
                fields.add("cantilever_span_one");	//	Пролёт
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("movement_surface");	//	Передвижение МПУ (поверхность)
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("crane_speed");	//	Скорость передвиж. крана / МПУ, механизмы
                fields.add("turn_speed");	//	Скорость поворота, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_crane_movement");	//	Концевые переключатели передвиж. крана
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_value_four");	//	Схема, значение 4
                fields.add("scheme_value_five");	//	Схема, значение 5
                fields.add("scheme_lifting_height");	//	Схема, в/п
                fields.add("scheme_rail_one");	//	Схема, рельс 1
                break;

            //Кран консольный стационарный
            case "CANTILEVER_STATIONARY_CRANE":
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("turn_speed");	//	Скорость поворота, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_crane_break");	//	тормоз крана
                fields.add("is_turn_limiter");	//	тормоз поворота
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_turn_console");	//	Концевые переключатели поворота консоли
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_value_four");	//	Схема, значение 4
                fields.add("scheme_value_five");	//	Схема, значение 5
                fields.add("scheme_lifting_height");	//	Схема, в/п
                break;

            //Кран консольный настенный
            case "WALL_MOUNTED_CANTILEVER_CRANE":
                fields.add("management_type");	//	Тип управления (поворотом / передвижением)
                fields.add("crane_current_line");	//	токопровод к крану
                fields.add("telpher_current_line");	//	токопровод механизма подъема
                fields.add("is_standard_speed");	//	Механизмы стандартно
                fields.add("lifting_speed");	//	Скорость подъема, механизмы
                fields.add("turn_speed");	//	Скорость поворота, механизмы
                fields.add("telpher_speed");	//	Скорость тельфера / телеги / тали, механизмы
                fields.add("is_lift_break");	//	тормоз подъема
                fields.add("is_turn_limiter");	//	тормоз поворота
                fields.add("is_telpher_break");	//	тормоз тельфера
                fields.add("is_frequency_converter");	//	частотный преобразователь
                fields.add("is_limit_switches_turn_console");	//	Концевые переключатели поворота консоли
                fields.add("is_limit_switches_telpher_movement");	//	Концевые переключатели передвиж. тельфера
                fields.add("is_limit_switches_telpher_lift");	//	Концевые переключатели подъема тельфера
                fields.add("scheme_value_one");	//	Схема, значение 1
                fields.add("scheme_value_two");	//	Схема, значение 2
                fields.add("scheme_value_three");	//	Схема, значение 3
                fields.add("scheme_lifting_height");	//	Схема, в/п
                break;

            // ошибка если не совпал тип ни с одним
            default:
                throw new ResourceNotFoundException("Тип крана не существует");
        }

        response.setPropertyList(fields);
        return response;
    }

    @Override
    public ResponseGetCraneType getCraneType() {
        ResponseGetCraneType response = new ResponseGetCraneType();
        Map<String, String> fields = new HashMap<>();

        // TODO не кастыльно сделать а придумать как брать из енума
        fields.put("OVERHEAD_ELECTRIC_SINGLE_GIRDER_SUPPORT_CRANE","Кран мостовой электрический однобалочный опорный");
        fields.put("OVERHEAD_ELECTRIC_SINGLE_GIRDER_SUPPORT_BOX_CRANE","Кран мостовой электрический однобалочный опорный коробчатый");
        fields.put("OVERHEAD_ELECTRIC_DOUBLE_GIRDER_SUPPORT_CRANE","Кран мостовой электрический двухбалочный опорный");
        fields.put("SEMI_CRANE_ELECTRIC_SINGLE_GIRDER","Кран полукозловой электрический однобалочный");
        fields.put("OVERHEAD_ELECTRIC_SINGLE_GIRDER_SUSPENDED_BOX_CRANE","Кран мостовой электрический однобалочный подвесной коробчатый");
        fields.put("BRIDGE_CRANE_ELECTRIC_SINGLE_GIRDER_SUSPENDED_DOUBLE_SPAN","Кран мостовой электрический однобалочный подвесной 2-х пролетный");
        fields.put("OVERHEAD_ELECTRIC_DOUBLE_GIRDER_OVERHEAD_CRANE","Кран мостовой электрический двухбалочный подвесной");
        fields.put("OVERHEAD_ELECTRIC_DOUBLE_GIRDER_SUSPENDED_DOUBLE_SPAN_CRANE","Кран мостовой электрический двухбалочный подвесной двухпролетный");
        fields.put("BRIDGE_CRANE_SUSPENDED_MANUAL","Кран мостовой подвесной ручной");
        fields.put("ELECTRIC_SINGLE_GIRDER_GANTRY_CRANE","Кран козловой электрический однобалочный");
        fields.put("GANTRY_CRANE_ELECTRIC_TRUSS","Кран козловой электрический ферменный");
        fields.put("ELECTRIC_DOUBLE_GIRDER_GANTRY_CRANE","Кран козловой электрический двухбалочный");
        fields.put("MOBILE_RELOADING_DEVICE_MPU","Мобильное перегрузочное устройство (МПУ)");
        fields.put("CANTILEVER_STATIONARY_CRANE","Кран консольный стационарный");
        fields.put("WALL_MOUNTED_CANTILEVER_CRANE","Кран консольный настенный");

        response.setCraneType(fields);
        return response;
    }


    private Crane checkCraneFields(Crane crane){
        // Механизмы = Стандартно
        if (crane.getIsStandardSpeed()) {
            crane.setLiftingSpeed(null);
            crane.setCraneSpeed(null);
            crane.setTurnSpeed(null);
            crane.setTelpherSpeed(null);
        }

        // Без консолей
        if (crane.getIsWithoutConsole()) {
            crane.setConsole(null);
            crane.setRightConsole(null);
            crane.setLeftConsole(null);
        }

        /* Полный список
                crane.setCantileverSpanOne(null); // Пролёт
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setLiftingHeight(null); // Высота подъёма
                crane.setIsServicePlatform(null); // площадка обслуживания
                crane.setIsTwoLift(null); // два подъема
                crane.setIsTwoCart(null); // две телеги
                crane.setManagementType(null); // тип управления (поворотом / передвижением)
                crane.setIsRadioControl(null); // тип управления радиоуправление
                crane.setIsRemoteControl(null); // тип управления подвесной пульт
                crane.setIsCabinControl(null); // тип управления Из кабины
                crane.setMovementSurface(null); // передвижение МПУ (поверхность)
                crane.setIsWithoutConsole(null); // без консолей
                crane.setConsole(null); // консоль
                crane.setRightConsole(null); // консоль правая
                crane.setLeftConsole(null); // консоль левая
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setCraneCurrentLine(null); // токопровод к крану
                crane.setTelpherCurrentLine(null); // токопровод механизма подъема
                crane.setIsStandardSpeed(null); // Механизмы стандартно
                crane.setLiftingSpeed(null); // Скорость подъема, механизмы
                crane.setCraneSpeed(null); // Скорость передвиж. крана / МПУ, механизмы
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setTelpherSpeed(null); // Скорость тельфера / телеги / тали, механизмы
                crane.setIsLiftBreak(null); // тормоз подъема
                crane.setIsCraneBreak(null); // тормоз крана
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsTelpherBreak(null); // тормоз тельфера / телеги / тали
                crane.setIsFrequencyConverter(null); // частотный преобразователь
                crane.setIsLimitSwitchesCraneMovement(null); // Концевые переключатели передвиж. крана
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setIsLimitSwitchesTelpherMovement(null); // Концевые переключатели передвиж. тельфера
                crane.setIsLimitSwitchesTelpherLift(null); // Концевые переключатели подъема тельфера
                crane.setBeam(null);// Балка
                crane.setSchemeValueOne(null); // Схема, значение 1
                crane.setSchemeValueTwo(null); // Схема, значение 2
                crane.setSchemeValueThree(null); // Схема, значение 3
                crane.setSchemeValueFour(null); // Схема, значение 4
                crane.setSchemeValueFive(null); // Схема, значение 5
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeLiftingHeight(null); // Схема, в/п
                crane.setSchemeRailOne(null); // Схема, рельс 1
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;
        */

        // Null-им то, чего нет у данного типа крана, даже если прислали несуществующие параметры
        switch (crane.getType()){
            case OVERHEAD_ELECTRIC_SINGLE_GIRDER_SUPPORT_CRANE: //Кран мостовой электрический однобалочный опорный
            case OVERHEAD_ELECTRIC_SINGLE_GIRDER_SUPPORT_BOX_CRANE: //Кран мостовой электрический однобалочный опорный коробчатый
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setIsServicePlatform(null); // площадка обслуживания
                crane.setManagementType(null); // тип управления (поворотом / передвижением)
                crane.setMovementSurface(null); // передвижение МПУ
                crane.setIsWithoutConsole(null); // без консолей
                crane.setConsole(null); // консоль
                crane.setRightConsole(null); // консоль правая
                crane.setLeftConsole(null); // консоль левая
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setCraneSpeed(null); // Скорость передвиж. крана / МПУ, механизмы
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setSchemeValueFour(null); // Схема, значение 4
                crane.setSchemeValueFive(null); // Схема, значение 5
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            //Кран мостовой электрический двухбалочный опорный
            case OVERHEAD_ELECTRIC_DOUBLE_GIRDER_SUPPORT_CRANE:
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setIsServicePlatform(null); // площадка обслуживания
                crane.setManagementType(null); // тип управления (поворотом / передвижением)
                crane.setMovementSurface(null); // передвижение МПУ
                crane.setConsole(null); // консоль
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setCraneSpeed(null); // Скорость передвиж. крана / МПУ, механизмы
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            // Кран полукозловой электрический однобалочный
            case SEMI_CRANE_ELECTRIC_SINGLE_GIRDER:
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setManagementType(null); // тип управления (поворотом / передвижением)
                crane.setMovementSurface(null); // передвижение МПУ (поверхность)
                crane.setRightConsole(null); // консоль правая
                crane.setLeftConsole(null); // консоль левая
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setSchemeValueFour(null); // Схема, значение 4
                crane.setSchemeValueFive(null); // Схема, значение 5
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                break;

            // Кран мостовой электрический однобалочный подвесной коробчатый
            case OVERHEAD_ELECTRIC_SINGLE_GIRDER_SUSPENDED_BOX_CRANE:
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setIsServicePlatform(null); // площадка обслуживания
                crane.setManagementType(null); // тип управления (поворотом / передвижением)
                crane.setMovementSurface(null); // передвижение МПУ
                crane.setIsWithoutConsole(null); // без консолей
                crane.setConsole(null); // консоль
                crane.setRightConsole(null); // консоль правая
                crane.setLeftConsole(null); // консоль левая
                crane.setCraneSpeed(null); // Скорость передвиж. крана / МПУ, механизмы
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setSchemeValueFour(null); // Схема, значение 4
                crane.setSchemeValueFive(null); // Схема, значение 5
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            // Кран мостовой электрический однобалочный подвесной двухпролетный
            case BRIDGE_CRANE_ELECTRIC_SINGLE_GIRDER_SUSPENDED_DOUBLE_SPAN:
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setManagementType(null); // тип управления (поворотом / передвижением)
                crane.setIsCabinControl(null); // тип управления Из кабины
                crane.setMovementSurface(null); // передвижение МПУ (поверхность)
                crane.setConsole(null); // консоль
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            // Кран мостовой электрический двухбалочный подвесной
            case OVERHEAD_ELECTRIC_DOUBLE_GIRDER_OVERHEAD_CRANE:
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setManagementType(null); // тип управления (поворотом / передвижением)
                crane.setMovementSurface(null); // передвижение МПУ (поверхность)
                crane.setConsole(null); // консоль
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            // Кран мостовой электрический двухбалочный подвесной двухпролетный
            case OVERHEAD_ELECTRIC_DOUBLE_GIRDER_SUSPENDED_DOUBLE_SPAN_CRANE:
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setManagementType(null); // тип управления (поворотом / передвижением)
                crane.setMovementSurface(null); // передвижение МПУ (поверхность)
                crane.setConsole(null); // консоль
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            // Кран мостовой подвесной ручной
            case BRIDGE_CRANE_SUSPENDED_MANUAL:
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setIsServicePlatform(null); // площадка обслуживания
                crane.setIsRadioControl(null); // тип управления радиоуправление
                crane.setIsRemoteControl(null); // тип управления подвесной пульт
                crane.setIsCabinControl(null); // тип управления Из кабины
                crane.setMovementSurface(null); // передвижение МПУ
                crane.setConsole(null); // консоль
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setCraneCurrentLine(null); // токопровод к крану
                crane.setTelpherCurrentLine(null); // токопровод механизма подъема
                crane.setIsStandardSpeed(null); // Механизмы стандартно
                crane.setLiftingSpeed(null); // Скорость подъема, механизмы
                crane.setCraneSpeed(null); // Скорость передвиж. крана / МПУ, механизмы
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setTelpherSpeed(null); // Скорость тельфера / телеги / тали, механизмы
                crane.setIsLiftBreak(null); // тормоз подъема
                crane.setIsCraneBreak(null); // тормоз крана
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsTelpherBreak(null); // тормоз тельфера / телеги / тали
                crane.setIsFrequencyConverter(null); // частотный преобразователь
                crane.setIsLimitSwitchesCraneMovement(null); // Концевые переключатели передвиж. крана
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setIsLimitSwitchesTelpherMovement(null); // Концевые переключатели передвиж. тельфера
                crane.setIsLimitSwitchesTelpherLift(null); // Концевые переключатели подъема тельфера
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            case ELECTRIC_SINGLE_GIRDER_GANTRY_CRANE: //Кран козловой электрический однобалочный
            case GANTRY_CRANE_ELECTRIC_TRUSS: //Кран козловой электрический ферменный
            case ELECTRIC_DOUBLE_GIRDER_GANTRY_CRANE: //Кран козловой электрический двухбалочный
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setManagementType(null); // тип управления (поворотом / передвижением)
                crane.setMovementSurface(null); // передвижение МПУ
                crane.setConsole(null); // консоль
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setCraneSpeed(null); // Скорость передвиж. крана / МПУ, механизмы
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота консоли
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            //Мобильное перегрузочное устройство (МПУ)
            case MOBILE_RELOADING_DEVICE_MPU:
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setCantileverDeparture(null); // Вылет консоли
                crane.setTurnAngle(null); // Угол поворота
                crane.setIsServicePlatform(null); // площадка обслуживания
                crane.setIsRadioControl(null); // тип управления радиоуправление
                crane.setIsRemoteControl(null); // тип управления подвесной пульт
                crane.setIsCabinControl(null); // тип управления Из кабины
                crane.setIsWithoutConsole(null); // без консолей
                crane.setConsole(null); // консоль
                crane.setRightConsole(null); // консоль правая
                crane.setLeftConsole(null); // консоль левая
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setTelpherCurrentLine(null); // токопровод механизма подъема
                crane.setTurnSpeed(null); // Скорость поворота, механизмы
                crane.setIsTurnLimiter(null); // тормоз поворота
                crane.setIsLimitSwitchesTurnConsole(null); // Концевые переключатели поворота крана
                crane.setBeam(null);// Балка
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            //Кран консольный стационарный
            case CANTILEVER_STATIONARY_CRANE:
                crane.setCantileverSpanOne(null); // Пролёт
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setIsServicePlatform(null); // площадка обслуживания
                crane.setIsRadioControl(null); // тип управления радиоуправление
                crane.setIsRemoteControl(null); // тип управления подвесной пульт
                crane.setIsCabinControl(null); // Из кабины
                crane.setMovementSurface(null); // передвижение МПУ
                crane.setIsWithoutConsole(null); // без консолей
                crane.setConsole(null); // консоль
                crane.setRightConsole(null); // консоль правая
                crane.setLeftConsole(null); // консоль левая
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setCraneSpeed(null); // Скорость передвиж. крана / МПУ, механизмы
                crane.setIsCraneBreak(null); // тормоз крана
                crane.setIsLimitSwitchesCraneMovement(null); // Концевые переключатели передвиж. крана
                crane.setBeam(null);// Балка
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailOne(null); // Схема, рельс 1
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            //Кран консольный настенный
            case WALL_MOUNTED_CANTILEVER_CRANE:
                crane.setCantileverSpanOne(null); // Пролёт
                crane.setCantileverSpanTwo(null); // Пролёт, дополнительный
                crane.setIsServicePlatform(null); // площадка обслуживания
                crane.setIsRadioControl(null); // тип управления радиоуправление
                crane.setIsRemoteControl(null); // тип управления подвесной пульт
                crane.setIsCabinControl(null); // Из кабины
                crane.setMovementSurface(null); // передвижение МПУ
                crane.setIsWithoutConsole(null); // без консолей
                crane.setConsole(null); // консоль
                crane.setRightConsole(null); // консоль правая
                crane.setLeftConsole(null); // консоль левая
                crane.setBridgeGallery(null); // галерея вдоль моста
                crane.setCraneSpeed(null); // Скорость передвиж. крана / МПУ, механизмы
                crane.setIsCraneBreak(null); // тормоз крана
                crane.setIsLimitSwitchesCraneMovement(null); // Концевые переключатели передвиж. крана
                crane.setBeam(null);// Балка
                crane.setSchemeValueFour(null); // Схема, значение 4
                crane.setSchemeValueFive(null); // Схема, значение 5
                crane.setSchemeValueSix(null); // Схема, значение 6
                crane.setSchemeValueSeven(null); // Схема, значение 7
                crane.setSchemeValueEight(null); // Схема, значение 8
                crane.setSchemeValueNine(null); // Схема, значение 9
                crane.setSchemeValueTen(null); // Схема, значение 10
                crane.setSchemeRailOne(null); // Схема, рельс 1
                crane.setSchemeRailTwo(null); // Схема, рельс 2
                break;

            // ошибка если не совпал тип ни с одним
            default:
                throw new ResourceNotFoundException("Тип крана не существует");
        }

        return crane;
    }


}
