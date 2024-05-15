with open("uebersetzungen.csv",'r') as file:

    e = file.readline()
    lst = e.strip().split(";")
    with open("src/main/resources/messages.properties",'w') as f:
        for line in file:
            if line.strip() and not line.startswith('#'):
                parts = line.strip().split(";")
                f.write(parts[0]+"="+parts[1]+"\n")
        file.seek(0)
        file.readline()
    for element in range(len(lst)-1):
        kuerzel = lst[element+1]
        
        with open("src/main/resources/messages_"+kuerzel+".properties",'w') as f:
            for line in file:
                if line.strip() and not line.startswith('#'):
                    parts = line.strip().split(";")
                    f.write(parts[0]+"="+parts[element+1]+"\n")
            file.seek(0)
            file.readline()
