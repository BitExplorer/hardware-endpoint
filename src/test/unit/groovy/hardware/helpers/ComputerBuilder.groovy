package hardware.helpers

import hardware.domain.Computer

class ComputerBuilder {

    String computerName = 'foo'
    String processor = 'intel'
    String processorCodename = 'haswell'
    String motherboard = "asus"
    String motherboardChipset = 'lga 1150'
    String primaryOperatingSystem = 'archlinux'
    String secondaryOperatingSystem = 'windows 10'
    String location = 'office'
    String videoCard = 'gtx 960'
    String videoCardCodename = 'unknown'
    String formFactor = 'desktop'
    String opticalDrive = 'blueray'
    String solidStateDrive = '500GB'
    String spinningDrive = '1TB'
    String memorySize = '16 GB DDR 4'
    String powerSupplyUnit = '650 WATT'
    String hdmiPort = '1'
    String networkPort = '1'
    String notes = 'my first computer'
    
    static ComputerBuilder builder() {
        return new ComputerBuilder()
    }

    Computer build() {
        Computer computer = new Computer()

        computer.computerName = computerName
        computer.processor = processor
        computer.processorCodename = processorCodename
        computer.motherboard = motherboard
        computer.motherboardChipset = motherboardChipset
        computer.primaryOperatingSystem = primaryOperatingSystem
        computer.secondaryOperatingSystem = secondaryOperatingSystem
        computer.location = location
        computer.videoCard = videoCard
        computer.videoCardCodename = videoCardCodename
        computer.formFactor = formFactor
        computer.opticalDrive = opticalDrive
        computer.solidStateDrive = solidStateDrive
        computer.spinningDrive = spinningDrive
        computer.memorySize = memorySize
        computer.powerSupplyUnit = powerSupplyUnit
        computer.hdmiPort = hdmiPort
        computer.networkPort = networkPort
        computer.notes = notes

        return computer
    }
}
