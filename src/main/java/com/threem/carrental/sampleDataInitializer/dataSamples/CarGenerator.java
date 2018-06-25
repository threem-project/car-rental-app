package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.EquipmentEntity;
import com.threem.carrental.app.model.entity.enumTypes.*;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.CarRepository;
import com.threem.carrental.app.repository.EquipmentRepository;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author marek_j on 2018-06-21
 */
public class CarGenerator {

    BranchRepository branchRepository;
    CarRepository carRepository;
    EquipmentRepository equipmentRepository;
    Random random = new Random();

    public CarGenerator(BranchRepository branchRepository, CarRepository carRepository, EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
        this.branchRepository = branchRepository;
        this.carRepository = carRepository;
    }

    public List<EquipmentEntity> generateAndSaveEquipmentBeforeCars() {
        List<EquipmentEntity> equipmentEntities = new EquipmentGenerator().generateEquipment();
        equipmentRepository.saveAll(equipmentEntities);
        return equipmentEntities;
    }

    public List<CarEntity> generateAndSaveCars(Integer numberOfSamples) {
        List<BranchEntity> branchList = branchRepository.findAll();
        List<CarEntity> entities = new ArrayList<>();
        for (int i = 0; i < numberOfSamples; i++) {
            CarEntity carEntity = generateRandomCar();
            Integer index = random.nextInt(branchList.size());
            BranchEntity branchEntity = branchList.get(index);
            carEntity.setBranch(branchEntity);
            carRepository.save(carEntity);
            entities.add(carEntity);
        }
        return entities;
    }

    private CarEntity generateRandomCar() {
        Map<String,String> makeAndModel = generateRandomMakeAndModel();

        CarEntity carEntity = CarEntity.builder()
                .id(null)
                .vin(generateVin())
                .make(makeAndModel.get("make"))
                .model(makeAndModel.get("model"))
                .segment(assignCarSegment())
                .seats(generateSeatsNumber())
                .doors(generateDoorsNumber())
                .bodyType(assignBodyType())
                .colour(assignCarColour())
                .engineType(assignEngineType())
                .engineCapacity(generateEngineCapacity())
                .transmission(assignTransmissionType())
                .year(assignYear())
                .dailyRate(generateDailyRate())
                .equipment(assignEquipment())
                .mileage(generateMileage())
                .status(assignStatus())
                .branch(null)
                .build();
        return carEntity;
    }

    private List<EquipmentEntity> assignEquipment() {
        List<EquipmentEntity> possibleEquipment = equipmentRepository.findAll();
        Integer equipmentElementsToAssign = random.nextInt(possibleEquipment.size());

        List<EquipmentEntity> equipmentResultList = new ArrayList<>();
        for (int i=0;i<equipmentElementsToAssign;i++) {
            equipmentResultList.add(possibleEquipment.get(i));
        }

        return equipmentResultList;
    }

    private String generateVin() {
        List<String> characters = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K");
        List<String> digits = Arrays.asList("1","2","3","4","5","6","7","8","9","0");
        List<List<String>> vinElements = Arrays.asList(characters,digits);
        Integer vinNumberLength = 17;
        String vin = "";
        for (int i = 0; i<vinNumberLength; i++) {
            Integer indexOfList = random.nextInt(vinElements.size());
            List<String> listToGetRandomElement = vinElements.get(indexOfList);
            Integer randomCharacterIndex = random.nextInt(listToGetRandomElement.size());
            vin = vin + listToGetRandomElement.get(randomCharacterIndex);
        }
        return vin;
    }

    private CarStatusEnum assignStatus() {
        List<CarStatusEnum> statusEnums = Arrays.asList(CarStatusEnum.AVAILABLE,
                CarStatusEnum.IN_REPAIR,
                CarStatusEnum.IN_USE,
                CarStatusEnum.DECOMISSIONED);
        Integer randomIndex = random.nextInt(statusEnums.size());
        return statusEnums.get(randomIndex);
    }

    private Integer generateMileage() {
        return random.nextInt(10000);
    }

    private Integer assignYear() {
        List<Integer> year = Arrays.asList(2014,2015,2016,2017,2018);
        Integer randomIndex = random.nextInt(year.size());
        return year.get(randomIndex);
    }

    private BigDecimal generateDailyRate() {
        List<BigDecimal> dailyRates = Arrays.asList(new BigDecimal(10.00),
                                                    new BigDecimal(10.50),
                                                    new BigDecimal(11.00));
        Integer randomIndex = random.nextInt(dailyRates.size());
        return dailyRates.get(randomIndex);
    }

    private CarSegmentTypeEnum assignCarSegment() {
        List<CarSegmentTypeEnum> carSegmentTypeEnumList = Arrays.asList(CarSegmentTypeEnum.A_MINI,
                CarSegmentTypeEnum.B_SMALL,
                CarSegmentTypeEnum.C_MEDIUM,
                CarSegmentTypeEnum.D_LARGE,
                CarSegmentTypeEnum.E_EXECUTIVE,
                CarSegmentTypeEnum.F_LUXURY,
                CarSegmentTypeEnum.J_SPORT_UTILITY_AND_OFF_ROAD,
                CarSegmentTypeEnum.M_MULTI_PURPOSE,
                CarSegmentTypeEnum.S_SPORTS);
        Integer index = random.nextInt(carSegmentTypeEnumList.size());
        return carSegmentTypeEnumList.get(index);
    }

    private CarTransmissionTypeEnum assignTransmissionType() {
        List<CarTransmissionTypeEnum> transmissionTypeEnums = Arrays.asList(CarTransmissionTypeEnum.MANUAL,
                                                                            CarTransmissionTypeEnum.AUTOMATIC);
        Integer randomIndex = random.nextInt(transmissionTypeEnums.size());
        return transmissionTypeEnums.get(randomIndex);
    }

    private Integer generateEngineCapacity() {
        Integer capacity = random.nextInt(3500);
        if (capacity<1000) {
            capacity+=1000;
        }
        return capacity;
    }


    private CarEngineTypeEnum assignEngineType() {
        List<CarEngineTypeEnum> carEngineTypeEnums = Arrays.asList(CarEngineTypeEnum.PETROL,CarEngineTypeEnum.DIESEL);
        Integer randomIndex = random.nextInt(carEngineTypeEnums.size());
        return carEngineTypeEnums.get(randomIndex);
    }

    private CarBodyTypeEnum assignBodyType() {
        List<CarBodyTypeEnum> carBodyTypeEnumsList = Arrays.asList(CarBodyTypeEnum.SEDAN,
                CarBodyTypeEnum.ESTATE,
                CarBodyTypeEnum.COUPE,
                CarBodyTypeEnum.CROSSOVER_4x4,
                CarBodyTypeEnum.HATCHBACK,
                CarBodyTypeEnum.SUV);
        Integer randomIndex = random.nextInt(carBodyTypeEnumsList.size());
        return carBodyTypeEnumsList.get(randomIndex);
    }

    private CarColourEnum assignCarColour() {
        List<CarColourEnum> colours = Arrays.asList(CarColourEnum.WHITE,CarColourEnum.BLUE,CarColourEnum.RED);
        Integer randomIndex = random.nextInt(colours.size());
        return colours.get(randomIndex);
    }

    private Integer generateSeatsNumber() {
        List<Integer> seats = Arrays.asList(2,5);
        Integer randomIndex =  random.nextInt(seats.size());
        return seats.get(randomIndex);
    }

    private Integer generateDoorsNumber() {
        List<Integer> doors = Arrays.asList(3,4,5);
        Integer randomIndex =  random.nextInt(doors.size());
        return doors.get(randomIndex);
    }

    private Map<String,String> generateRandomMakeAndModel() {
        Map<String,List<String>> cars = initializeCars();
        String brand = getRandomBrandFromMap(cars);
        String model = getRandomModelFromList(cars.get(brand));
        Map<String,String> randomCar = new HashMap<>();
        randomCar.put("make",brand);
        randomCar.put("model",model);
        return randomCar;
    }

    private String getRandomModelFromList(List<String> models) {
        Integer randomIndex = random.nextInt(models.size());
        return models.get(randomIndex);
    }

    private String getRandomBrandFromMap(Map<String,List<String>> makeAndModel) {
        Object[] brandArray = makeAndModel.keySet().toArray();
        Integer randomBrandIndex =  random.nextInt(brandArray.length);
        Object resultBrand =  brandArray[randomBrandIndex];
        return resultBrand.toString();
    }

    private Map<String,List<String>> initializeCars() {
        Map<String,List<String>> makeAndModel = new HashMap<>();
        makeAndModel = initializeAudi(makeAndModel);
        makeAndModel = initializeVolkswagen(makeAndModel);
        makeAndModel = initializeMercedes(makeAndModel);
        makeAndModel = initializeSkoda(makeAndModel);
        makeAndModel = initializeFord(makeAndModel);
        return makeAndModel;
    }

    private Map<String,List<String>> initializeAudi(Map<String,List<String>> makeAndModel) {
        Map<String,List<String>> cars = new HashMap<>(makeAndModel);
        List<String> models = Arrays.asList("A3","A4","A6","Q3","Q5");
        cars.put("Audi",models);
        return cars;
    }

    private Map<String,List<String>> initializeVolkswagen(Map<String,List<String>> makeAndModel) {
        Map<String,List<String>> cars = new HashMap<>(makeAndModel);
        List<String> models = Arrays.asList("Golf","Passat","Tiguan");
        cars.put("Volkswagen",models);
        return cars;
    }

    private Map<String,List<String>> initializeMercedes(Map<String,List<String>> makeAndModel) {
        Map<String,List<String>> cars = new HashMap<>(makeAndModel);
        List<String> models = Arrays.asList("A","B","C","E");
        cars.put("Mercedes",models);
        return cars;
    }

    private Map<String,List<String>> initializeSkoda(Map<String,List<String>> makeAndModel) {
        Map<String,List<String>> cars = new HashMap<>(makeAndModel);
        List<String> models = Arrays.asList("Fabia","Rapid","Octavia");
        cars.put("Skoda",models);
        return cars;
    }

    private Map<String,List<String>> initializeFord(Map<String,List<String>> makeAndModel) {
        Map<String,List<String>> cars = new HashMap<>(makeAndModel);
        List<String> models = Arrays.asList("Focus","Mondeo");
        cars.put("Ford",models);
        return cars;
    }

    private class EquipmentGenerator {

        private List<EquipmentEntity> generateEquipment() {
            List<EquipmentEntity> equipment = new ArrayList<>();
            equipment.add(new EquipmentEntity(null,"Air Conditioning"));
            equipment.add(new EquipmentEntity(null,"Navigation"));
            equipment.add(new EquipmentEntity(null,"Internet Connection"));
            return equipment;
        }
    }
}
