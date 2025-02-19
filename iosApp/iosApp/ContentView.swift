import SwiftUI
import shared

struct ContentView: View {
    @State private var permissionStatus: String = "Requesting Microphone Permission..."

    func askForMicrophonePermission() {
        Permissions.shared.askForMicrophonePermission { granted in
            if granted.boolValue {
                permissionStatus = "Microphone permission granted"
            } else {
                permissionStatus = "Microphone permission denied"
            }
        }
    }

	let greet = Greeting().greet()
    @StateObject private var viewModel = TimelineViewModel()

    func sumOperations() -> Int {
        return Int(MathOperations().add(a: 5, b: 3))
     }

    func subOperations() -> Int {
        return Int(MathOperations().sub(a: 5, b: 3))
     }

    var body: some View {
        VStack(spacing: 20) {
            Text(permissionStatus)
            .padding()
            Button("Request Permission") {
                askForMicrophonePermission()
            }
            .padding()
            .background(Color.blue)
            .foregroundColor(.white)
            .cornerRadius(10)


            Text(viewModel.timeDisplay)

                HStack {
                    Button("Start Fetching") {
                        viewModel.startFetching()
                    }
                    .padding()
                    .background(Color.green)
                    .foregroundColor(.white)
                    .cornerRadius(10)

                    Button("Stop Fetching") {
                        viewModel.stopFetching()
                    }
                    .padding()
                    .background(Color.red)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                }

                Text(viewModel.manifestDisplay)

                Button("Fetch Manifest") {
                    viewModel.startFetchingManifest()
                }
                .padding()
                .background(Color.green)
                .foregroundColor(.white)
                .cornerRadius(10)

            }
            .padding()
        }

}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
