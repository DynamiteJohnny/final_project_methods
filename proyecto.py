import ciw
import math


print("\nMétodos Cuantitativos y simulación\nAlumnos:\n\tShara Teresa González Mena\n\tEsteban Quintana Cueto\n\tJuan Luis Suarez Ruiz\n")
print("\n Proyecto final: Simulación de un sistema de colas\n")

l = float(input("\n Introduce el valor de lambda: "))
miu = float(input("\n Introduce el valor de miu: "))
servers = int(input("\n Introduce el número de servidores: "))

# Define what the system looks like by creating a network object: number of servers, arrival distribution (lambda)
# and service distribution (miu)
System = ciw.create_network(
    # Specify that the distribution of arrivals is exponential
    Arrival_distributions = [['Exponential', l]],
    Service_distributions = [['Exponential', miu]],
    Number_of_servers = [servers]
)

p = l / (servers * miu)
aux = l / miu

aux1 = 0
for n in range(servers):
    aux1 += pow(aux, n) / math.factorial(n)
aux2 = pow(aux, servers) / (math.factorial(servers) * (1 - p))

p0 = 1 / (aux1 + aux2)
lq = (pow(aux, servers) * p0 * p) / (math.factorial(servers) * pow(1 - p, 2))
wq = lq / l
ws = wq + 1 / miu
ls = l * ws

print("\n---- MEDIDAS DE DESEMPEÑO -------\n")
print("\tlambda=%f  \n\tmiu=%f  \n\tNumero de Servidores=%d" % (l, miu, servers))
print("p=%f  p0=%f  Ls=%f  Lq=%f  Ws=%f  Wq=%f\n" % (p, p0, ls, lq, ws, wq))


# Set a seed
ciw.seed(1)

# Object Sim is created with three nodes: arrival node where customers are created (Sim.nodes[0]), service node where
#customers queue up to receive service (Sim.nodes[1]) and the exit node where customers leave the system (Sim.nodes[-1])
Sim = ciw.Simulation(System)

clients = int(input("\n Clientes: "))
    # The time is based on the unit of time used for calculating the miu and the lambda
Sim.simulate_until_max_customers(clients, progress_bar=False)

records = Sim.get_all_records()
records.sort()

Sim.write_records_to_file('simulation.txt', headers=True)


# After the simulation, the Sim object remains in the same state as it reached at the end so it can give you information

service_start = [r.service_start_date for r in records]
queue_size_at_departure = [r.queue_size_at_departure for r in records]
arrival_date = [r.arrival_date for r in records]
exit_date = [r.exit_date for r in records]

print("\n---- CLIENTES -----\n")

#De la guia de la libreria
service_time = [r.service_time for r in records]
waiting_time = [r.waiting_time for r in records]
mediaservicio = sum(service_time) / len(service_time)
mediaespera = sum(waiting_time) / len(waiting_time)
mediacola = sum(queue_size_at_departure) / len(queue_size_at_departure)

for i in range(len(records)):
    print("Cliente %d -> t espera: %0.4f | t servicio: %0.4f | Llegada: %0.4f | Salida: %0.4f" % (i, waiting_time[i], service_time[i], arrival_date[i], exit_date[i]))

print("\n---- ESTADÍSTICAS ----\n")
print("Máximo tiempo de espera: %f" % max(waiting_time))
print("Mínimo tiempo de espera: %f" % min(waiting_time))
print("Promedio de tiempo de servicio: %f" % mediaservicio)
print("Promedio de tiempo de espera: %f" % mediaespera)
print("Promedio de fila: %f" % mediacola)
print("\n")
