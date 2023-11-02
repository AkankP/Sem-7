import os

a = input("Enter path of your media ")
print("Please wait for a while")

# Use subprocess module to run 'dir' to list files and directories
os.system("dir " + a)

c = input("Enter the filename you want to recover:\n")

# Use chkdsk for file recovery
b = "chkdsk " + a + " /f /r"
os.system(b)

# After running chkdsk, you can check the recovered files in a directory called "found.000" or similar
recovered_directory = os.path.join(a, "found.000")
recovered_file_path = os.path.join(recovered_directory, c)
print(f"Recovered file path: {recovered_file_path}")

# Perform further analysis or operations on the recovered file
# You might use tools like 'foremost', 'testdisk', or 'scalpel' for more advanced recovery and analysis.

print("File recovered Successfully")
